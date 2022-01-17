import dao.CategoriaDAO;
import dao.ProdutoDAO;
import enums.ModeloCelular;
import modelo.Categoria;
import modelo.Produto;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class BuscaPorObjetos {
    public static void main(String[] args) {
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

        List<Produto> produtos = produtoDAO.todosProdutos();

        produtos.forEach(produto -> {
            System.out.println(produto.getNome());
        });

        System.out.println("-------------------------------------------------------");

        Produto produtoTeste = produtoDAO.produtoPorNome("MOTOROLA G00");
        System.out.println(produtoTeste.getNome());

        Produto produtoTesteDois = produtoDAO.produtoPorCategoria("LIVRO");
        System.out.println(produtoTesteDois.getNome());

        List<BigDecimal> precosDosProdutos = produtoDAO.precosDosProdutos();

        precosDosProdutos.forEach(preco -> {
            System.out.println(preco);
        });

        System.out.println("Teste com criteria.");
        List<Produto> produtoTesteCriteria = produtoDAO.buscarPorParametrosComCriteria(null,null);
        produtoTesteCriteria.forEach(produto -> {
            System.out.println(produto.getNome());
        });
    }
}
