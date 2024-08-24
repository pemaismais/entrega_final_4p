package app.gestao_loja.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"compras"})
    private Cliente cliente;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"vendas"})
    private Funcionario funcionario;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Itens empty")
    @NotEmpty
    @JsonIgnoreProperties({"venda"})
    private Set<ItemProduto> itensProduto;

    private double valorTotal;

}
