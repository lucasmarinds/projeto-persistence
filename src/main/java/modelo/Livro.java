package modelo;

import javax.persistence.Entity;
import java.math.BigDecimal;

import enums.ModeloCelular;
@Entity
public class Livro extends Produto{

    private Integer numeroPaginas;
    private String autor;

    public Livro(){}

    public Livro(Integer numeroPaginas, String autor) {
        this.numeroPaginas = numeroPaginas;
        this.autor = autor;
    }

    public Livro(String nomeProduto, String descricao, BigDecimal preco, ModeloCelular modeloCelular, Categoria categoria, Integer numeroPaginas, String autor) {
        super(nomeProduto, descricao, preco, modeloCelular, categoria);
        this.numeroPaginas = numeroPaginas;
        this.autor = autor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
