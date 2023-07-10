package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosAgendamentoConsultaConfirmacao agendar(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nesta data");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
        return new DadosAgendamentoConsultaConfirmacao(consulta, medico, paciente);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialida é obrigatória quando medico não for escolhido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public DadosCancelamentoConsultaConfirmacao cancelar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Agendamento de consulta não encontrado");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        System.out.println("CONSULTA ==> " + consulta.toString());
        var dataCancelamento = LocalDateTime.now();
        var dataConsultaAdendada = consulta.getData();
        long diferencaEmHoras = Duration.between(dataCancelamento, dataConsultaAdendada).toHours();
        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("O cancelamento só pode ser realizado com 24h de antecedência.");
        }

        consulta.cancelarAgendamento(dados);
        return new DadosCancelamentoConsultaConfirmacao("Cancelamento efetuado com sucesso.");
    }
}
