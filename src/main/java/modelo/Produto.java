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

    @ManyToOne
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
