package app.gestao_loja.repository;

import app.gestao_loja.entity.Cliente;
import app.gestao_loja.entity.Funcionario;
import app.gestao_loja.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Long> {
    public List<Venda> findByFuncionario(Funcionario funcionario);
    public List<Venda> findByCliente(Cliente cliente);

    @Query("SELECT v FROM Venda v WHERE v.valorTotal > :valor")
    public List<Venda> findVendaMaiorQue(double valor);

}
