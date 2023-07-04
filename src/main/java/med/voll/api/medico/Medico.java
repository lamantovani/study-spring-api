package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endreco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Enumerated
    private Endreco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endreco(dados.endereco());
    }


    public void atualizarInformacoes(DadosAtualizacaoMedico novoDado) {
        if (novoDado.nome() != null) {
            this.nome = novoDado.nome();
        }
        if (novoDado.telefone() != null) {
            this.telefone = novoDado.telefone();
        }
        if (novoDado.endereco() != null) {
            this.endereco.atualizaEndereco(novoDado.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
