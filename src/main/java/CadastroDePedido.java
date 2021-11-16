import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import enums.ModeloCelular;
import modelo.*;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
    EntityManager entityManager = JPAUtils.createEntityManager();
    cadastrarProdutos();
    ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
    Produto produto = produtoDAO.produtoPorNome("MOTOROLA G00");

    Cliente cliente = new Cliente("Lucas","37832870818");
    Pedido pedido = new Pedido(cliente);
    pedido.adicionarItem(new ItemPedido(3,produto,pedido));
    PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
    ClienteDAO clienteDAO = new ClienteDAO(entityManager);

    entityManager.getTransaction().begin();
    clienteDAO.cadastrar(cliente);
    pedidoDAO.cadastrar(pedido);
    entityManager.getTransaction().commit();
    }

    public static void cadastrarProdutos(){
        Categoria celulares = new Categoria("CELULAR");
        Categoria livraria = new Categoria("LIVRO");
        Produto celular = new Produto("IPHONE XR PRO MAX 28","Celular Bao",new BigDecimal("15500.00"), ModeloCelular.IPONE,celulares);
        Produto celularDois = new Produto("MOTOROLA G00","Celular BAD",new BigDecimal("1.00"), ModeloCelular.MOTOROLA,celulares);
        Produto celularTres = new Produto("XIAOMI PITHON","Celular PITUP",new BigDecimal("1222.00"), ModeloCelular.XIAOMI,celulares);
        Produto livro = new Produto("HEURI POTTI","COMO SER PEPERONI",new BigDecimal("8000.00"), null,livraria);
        EntityManager entityManager = JPAUtils.createEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();
        entityManager.persist(celulares);
        entityManager.persist(livraria);
        entityManager.persist(celular);
        entityManager.persist(celularDois);
        entityManager.persist(celularTres);
        entityManager.persist(livro);
        entityManager.getTransaction().commit();
    }
}
