package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaConfirmacao(Long id, LocalDateTime data, String nomeDoMedico, String nomeDoPaciente) {
    DadosAgendamentoConsultaConfirmacao(Consulta consulta, Medico medico, Paciente paciente) {
        this(consulta.getId(), consulta.getData(), medico.getNome(), paciente.getNome());
    }
}
