import dao.CategoriaDAO;
import dao.ProdutoDAO;
import enums.ModeloCelular;
import modelo.Categoria;
import modelo.Produto;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELUNOLAR");
        //Quando o Objeto apenas existe no java o JPA entende que essa parte do ciclo que nada foi feito com esse objeto ele é transient.
        Produto celular = new Produto("IPHONE XR PRO MAX 28","Celular Bao",new BigDecimal("15500.00"),ModeloCelular.IPONE,celulares);
        EntityManager entityManager = JPAUtils.createEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        /** Quando o objeto é persistido pelo entityManager no método persist, tudo que for alterado nesse mesmo
         * objeto, o JPA estará de olho porque ele ficará no modo de Managed a partir do momento que foi colocado
         * no persist, tudo que for alterado ou adicionado a esse objeto, antes do commit ou sincronizado
         * no banco, o JPA sabera alterar isso antes do commit no banco, nesse exemplo será um insert por causa do
         * persis e depois um update no mesmo objeto por causa que adicionamos um outro nome antes do commit.
         */
        produtoDAO.cadastrar(celular);
        celular.setNome("IPONE MARACUJA");
        /**
         * Se utilizar do clear, ele não faz nenhuma alteração e deixa os valores orignais que vieram para o objeto persistido
         * e a resposta será que o nome dele continuará a ser "IPHONE XR PRO MAX 28"
         */
        //entityManager.clear();
        /**
         * Mesmo que ocorrá qualquer alteração no nome ou valor do objeto depois do close desse entityManager o JPA irá ignorar.
         * que é o caso do IPONE MORANGO, ele será ignorado porque esse objeto persistido já estará no estado de detached depois
         * da linha que execute o entityManager.close(), e então ele não pode ser mais alterado.
         */
        entityManager.flush();
        entityManager.close();
        celular.setNome("IPONE MORANGO");
    }
}
