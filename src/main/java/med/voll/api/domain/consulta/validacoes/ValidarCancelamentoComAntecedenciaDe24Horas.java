package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarCancelamentoComAntecedenciaDe24Horas implements  ValidarCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var dataCancelamento = LocalDateTime.now();
        var dataConsultaAdendada = consulta.getData();
        long diferencaEmHoras = Duration.between(dataCancelamento, dataConsultaAdendada).toHours();
        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("O cancelamento só pode ser realizado com 24h de antecedência.");
        }
    }

}
