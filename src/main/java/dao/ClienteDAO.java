package dao;

import modelo.Cliente;
import modelo.Pedido;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente){
        this.entityManager.persist(cliente);
    }

    public String pegarPorNome(String nomeCliente) {
        String query = "SELECT c FROM Cliente c WHERE c.dadosPessoais.nome = :nome";
        Cliente cliente = this.entityManager.createQuery(query, Cliente.class)
                .setParameter("nome",nomeCliente)
                .getSingleResult();
        return cliente.getNome();
    }
}
