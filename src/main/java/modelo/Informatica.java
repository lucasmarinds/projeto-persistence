package modelo;

import javax.persistence.Entity;
import java.math.BigDecimal;

import enums.ModeloCelular;
@Entity
public class Informatica extends Produto{

    private String marca;
    private String modelo;

    public Informatica(){}

    public Informatica(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public Informatica(String nomeProduto, String descricao, BigDecimal preco, ModeloCelular modeloCelular, Categoria categoria, String marca, String modelo) {
        super(nomeProduto, descricao, preco, modeloCelular, categoria);
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
