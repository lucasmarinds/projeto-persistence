package modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    private Cliente cliente;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    /**
     * é Sempre necessário que tenha o mappedBy do lado sempre que tem o OneToMany com o nome do atributo
     * que nossa classe esta mapeada do outro lado, caso não coloque o mappedby ele vai criar uma tabela de innerjoin
     * apenas com as colunas que serão as chaves primarias das duas entidades.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList();
    //Utilizei da propriedade cascade para que na entidade ItemPedido seja já feito a persistencia do objeto também.

    public Pedido(){}

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarItem(ItemPedido item){
        this.valorTotal = this.valorTotal.add(item.getValorTotal());
        item.setPedido(this);
        this.itens.add(item);
    }
}
