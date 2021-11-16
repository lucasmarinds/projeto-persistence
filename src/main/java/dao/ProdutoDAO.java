package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto){
        this.entityManager.persist(produto);
    }

    public List todosProdutos(){
        String sql = "SELECT po FROM Produto po";
        return this.entityManager.createQuery(sql).getResultList();
    }

    public Produto produtoPorNome(String nome){
        String sql = "SELECT po FROM Produto po WHERE po.nomeProduto = ?1";
        return (Produto) this.entityManager.createQuery(sql).setParameter(1, nome).getSingleResult();
    }

    public Produto produtoPorCategoria(String categoria){
        String sql = "SELECT po FROM Produto po where po.categoria.nome = :categoria";
        return (Produto) this.entityManager.createQuery(sql).setParameter("categoria", categoria).getSingleResult();
    }

    public List precosDosProdutos() {
        String sql = "SELECT po.preco FROM Produto po";
        return this.entityManager
                .createQuery(sql).getResultList();
    }
}
