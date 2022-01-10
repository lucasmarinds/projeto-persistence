package vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RelatorioDeVendasVO {

    private Long quantidadeVendida;
    private String nomeProduto;
    private LocalDateTime dataUltimaVenda;

    public RelatorioDeVendasVO(String nomeProduto, Long quantidadeVendida, LocalDateTime dataUltimaVenda) {
        this.quantidadeVendida = quantidadeVendida;
        this.nomeProduto = nomeProduto;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public LocalDateTime getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVO{" +
                "quantidadeVendida=" + quantidadeVendida +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }
}
