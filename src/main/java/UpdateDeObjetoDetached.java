import dao.CategoriaDAO;
import dao.ProdutoDAO;
import enums.ModeloCelular;
import modelo.Categoria;
import modelo.Produto;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

/**
 * Quando o objeto está como detached, podemos voltar ela managed, para podermos fazer outros updates.
 */
public class UpdateDeObjetoDetached {
    public static void main(String[] args) {

        Categoria celulares = new Categoria("CELULAR");
        Produto celular = new Produto("IPHONE XR PRO MAX 28","Celular Bao",new BigDecimal("15500.00"), ModeloCelular.IPONE,celulares);
        EntityManager entityManager = JPAUtils.createEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();
        entityManager.persist(celulares);
        entityManager.persist(celular);
        // entityManager.clear(); // se deixar sem comentar essa linha o clear não deixará que o objeto seja atualizado para xiaomi 9T. pois depois do clear ele está já como detached.
        celular.setNome("XIAOMI 9T");
        entityManager.flush();
        entityManager.clear();
        /**
         * Quando precisamos utilizar do merge, ele faz um select no banco para trazer o objeto que já foi para detached
         * e quando ele trás ele trás um outro objeto então para que seja possivel fazer um update é necessário
         * que faça o objeto antigo que esta como detached apontar para o novo que o hibernate trouxe do banco para nós.
         */
        celular = entityManager.merge(celular);
        celular.setNome("Rodorfo");
        entityManager.flush();
        entityManager.getTransaction().commit();

    }


}
