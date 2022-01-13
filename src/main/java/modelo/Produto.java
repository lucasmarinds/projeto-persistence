package modelo;

import enums.ModeloCelular;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "Produto.produtoPorCategoria", query = "SELECT po FROM Produto po where po.categoria.nome = :categoria")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nome_produto")
    private String nomeProduto;

    @Column
    private String descricao;

    @Column
    private BigDecimal preco;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "modelo_celular")
    private ModeloCelular modeloCelular;

    /**
     * A importancia de colocar esse parametro na Anotação é para que quando seja feito um select nessa classe
     * o JPA não faça um select na tabela Categoria, porque o comportamento EAGER que é o padrão que segue as anotações
     * @algumaToOne faz com que quando chamar essa tabela, o jpa automaticamente faça um join nela também e se houver
     * outras tabelas dentro da tabela Categoria que também são @algumaToOne vai realizar um join neles também, gerando
     * muita usabilidade, fazendo ser custosa o uso de qualquer método DAO(data access object) dessa entidade, isso é
     * igual um efeito cascade que vai descendo e fazendo o mesmo para todos se colocarmos o comportamento Lazy, ele
     * não vai agir dessa maneira mudando seu comportamento para agir igual as anotações do tipo @algumaToMany,
     * que para ser feito um join com a tabela APENAS se o DAO ou parte da Aplicação chamar esse parametro que é uma
     * outra table.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Produto(){}

    public Produto(String nomeProduto, String descricao, BigDecimal preco, ModeloCelular modeloCelular, Categoria categoria) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.modeloCelular = modeloCelular;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nomeProduto;
    }

    public void setNome(String nomePessoa) {
        this.nomeProduto = nomePessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ModeloCelular getModeloCelular() {
        return modeloCelular;
    }

    public void setModeloCelular(ModeloCelular categoria) {
        this.modeloCelular = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
