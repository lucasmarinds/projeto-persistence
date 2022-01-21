import dao.ClienteDAO;
import modelo.Cliente;
import utils.JPAUtils;

import javax.persistence.EntityManager;

public class CadastroCliente {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtils.createEntityManager();
        Cliente cliente = new Cliente("Kakashiro", "111-333-444-55");
        Cliente clienteDois = new Cliente("Carlota", "234-321-447-51");
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        entityManager.getTransaction().begin();
        clienteDAO.cadastrar(cliente);
        clienteDAO.cadastrar(clienteDois);
        entityManager.flush();
        entityManager.getTransaction().commit();
        System.out.println(clienteDAO.pegarPorNome("Carlota"));
    }
}
