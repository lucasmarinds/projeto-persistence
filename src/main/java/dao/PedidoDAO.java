package dao;

import modelo.Pedido;
import vo.RelatorioDeVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {
    private EntityManager entityManager;

    public PedidoDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return this.entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    /**
     * É necessário utilizar de um Lista de um Array de Objeto porque como vão ser varios dados de tipos diferentes
     * pegos nos bancos como quantidade que é integer o nome que é string e a data que é um LocalDate devemos utilizar
     * da classe mais abstrata possivel que é a Object que todas herdam, e um array porque se formos pensar nesse
     * relatorio são 3 colunas, Nome/Quantidade/DataUltimaVenda -> logo para cada registro, cada coluna das informações
     * será uma posição do array cada registro tera 3 valores dentro do array.
     * @return retorna uma lista de array de object em que registro vai ser um array de object e cada valor do array é uma coluna.
     */
    public List<RelatorioDeVendasVO> relatorioDeVenda(){
        String jpql = "SELECT new vo.RelatorioDeVendasVO(" +
                "produto.nomeProduto, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data))" +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nomeProduto " +
                "ORDER BY item.quantidade DESC";
        return entityManager.createQuery(jpql, RelatorioDeVendasVO.class).getResultList();
    }

    /**
     * Esse tipo de busca que colocamos o JOIN FETCH serve para que caso seja fechado a conexão do entityManager
     * a gente ainda consiga trazer junto com a busca do pedido o carregamento do cliente sem que precisemos ter
     * que criar outro método para carregar o cliente, ou adicionar mais código antes do fechamento do entityManager e
     * o pior que seria tirar o fetch lazy da entidade Cliente porque se tirarmos toda consulta vai carregar essa tabela
     * e isso fica custoso como vimos na hora de colocarmos esse parametro nas anotações que são @ToOne.
     * @param id
     * @return
     */
    public Pedido buscarPedidoPeloCliente(Long id) {
        return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id ",Pedido.class)
                .setParameter("id",id)
                .getSingleResult();
    }
}
