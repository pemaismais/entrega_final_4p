package app.gestao_loja.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cliente extends Pessoa{
    @NotNull
    @CPF
    @Column(unique=true)
    private String cpf;
    @NotNull
    @Column(unique=true)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "Número de telefone inválido")
    private String telefone;

    @OneToMany(mappedBy = "cliente",  orphanRemoval = true)
    @JsonIgnoreProperties({"cliente"})
    private List<Venda> compras;
}
