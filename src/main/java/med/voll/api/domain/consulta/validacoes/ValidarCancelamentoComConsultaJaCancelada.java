package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCancelamentoComConsultaJaCancelada implements ValidarCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consultaJaCancelada = consultaRepository.findByInConsultaJaCancelada(dados.idConsulta());
        if (consultaJaCancelada) {
            throw new ValidacaoException("Consulta já foi cancelada anteriormente");
        }
    }
}
