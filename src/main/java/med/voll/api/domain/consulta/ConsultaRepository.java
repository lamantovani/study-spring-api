package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndData(Long idMecido, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    @Query("""
            SELECT c.motivoCancelamento IS NOT NULL FROM Consulta c
            WHERE
                c.id = :id
            """)
    boolean findByInConsultaJaCancelada(Long id);
}
