package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public List todosProdutos() {
        String sql = "SELECT po FROM Produto po";
        return this.entityManager.createQuery(sql).getResultList();
    }

    public Produto produtoPorNome(String nome) {
        String sql = "SELECT po FROM Produto po WHERE po.nomeProduto = ?1";
        return (Produto) this.entityManager.createQuery(sql).setParameter(1, nome).getSingleResult();
    }

    public Produto produtoPorCategoria(String categoria) {
        return this.entityManager.createNamedQuery("Produto.produtoPorCategoria", Produto.class).setParameter("categoria", categoria).getSingleResult();
    }

    public List precosDosProdutos() {
        String sql = "SELECT po.preco FROM Produto po";
        return this.entityManager
                .createQuery(sql).getResultList();
    }

    // Colocamos o "1=1" na frente do where só para aceitar que o where tenha uma condição na frente.
    public List<Produto> buscarPorParametros(String nome, BigDecimal preco){
        String jpql = "SELECT p FROM Produto p WHERE 1=1";
        if(nome != null && !nome.isEmpty()){
            jpql += "AND p.nome = :nome";
        }
        if(preco != null){
            jpql += "AND p.preco = :preco";
        }
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        if(nome != null && !nome.isEmpty()){
            query.setParameter("nome", nome);
        }
        if(preco != null){
            query.setParameter("preco", preco);
        }
        return query.getResultList();
    }
}
