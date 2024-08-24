package app.gestao_loja.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ItemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id")
   // @JsonBackReference
    private Venda venda;

    public Long getIdProduto() {
        return this.produto != null ? this.produto.getId() : null;
    }

    public double getValorProduto() {
        return this.produto != null ? this.produto.getValor() : 0.0;
    }
}
