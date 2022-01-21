package modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

    public DadosPessoais(){}

    @Column(name = "TESTE_NOME")
    private String nome;

    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
