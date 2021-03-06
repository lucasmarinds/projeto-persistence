import dao.*;
import enums.ModeloCelular;
import modelo.*;
import utils.JPAUtils;
import vo.RelatorioDeVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtils.createEntityManager();
        cadastrarProdutos();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        Produto produto = produtoDAO.produtoPorNome("HEURI POTTI");
        Produto produtoDois = produtoDAO.produtoPorNome("Playstation 5");
        Produto produtoTres = produtoDAO.produtoPorNome("Xbox SS");

        Cliente cliente = new Cliente("Lucas","37832870818");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(250,produto,pedido));
        pedido.adicionarItem(new ItemPedido(278,produtoDois,pedido));
        pedido.adicionarItem(new ItemPedido(321,produtoTres,pedido));
        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        entityManager.getTransaction().begin();
        clienteDAO.cadastrar(cliente);
        pedidoDAO.cadastrar(pedido);
        entityManager.getTransaction().commit();

        BigDecimal valorTotal = pedidoDAO.valorTotalVendido();
        System.out.println("Valor total vendido: "+valorTotal);

        List<RelatorioDeVendasVO> relatorioDeVenda = pedidoDAO.relatorioDeVenda();
        relatorioDeVenda.forEach(registro -> {
            System.out.println(registro);
        });

    }

    public static void cadastrarProdutos(){
        Categoria celulares = new Categoria("CELULAR");
        Categoria livraria = new Categoria("LIVRO");
        Categoria eletronico = new Categoria("ELETRONICOS");
        Produto xbox = new Produto("Xbox SS","Celular BAO",new BigDecimal("2000.00"), null,eletronico);
        Produto playstation = new Produto("Playstation 5","Celular BAD",new BigDecimal("4100.00"), null,eletronico);
        Produto celular = new Produto("IPHONE 11 PRO","Celular TOPPER",new BigDecimal("4500.00"), ModeloCelular.IPONE,celulares);
        Produto livro = new Produto("HEURI POTTI","COMO SER PEPERONI",new BigDecimal("8000.00"), null,livraria);
        EntityManager entityManager = JPAUtils.createEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();
        entityManager.persist(celulares);
        entityManager.persist(livraria);
        entityManager.persist(eletronico);
        entityManager.persist(celular);
        entityManager.persist(playstation);
        entityManager.persist(xbox);
        entityManager.persist(livro);
        entityManager.getTransaction().commit();

    }
}
