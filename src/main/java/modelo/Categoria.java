package modelo;

import javax.persistence.*;

@Entity
public class Categoria {

    @EmbeddedId
    private CategoriaId id = new CategoriaId();

    public Categoria(){}

    public Categoria(String nome){
        this.id.setNome(nome);
    }

    public String getNome() {
        return id.getNome();
    }

    public void setNome(String nome) {
        this.id.setNome(nome);
    }
}
