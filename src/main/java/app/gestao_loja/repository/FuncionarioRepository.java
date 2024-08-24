package app.gestao_loja.repository;

import app.gestao_loja.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    public Funcionario findByMatricula(String matricula);
    public Funcionario findByNomeContaining(String nome);

    @Query("SELECT f.nome, f.matricula, SUM(v.valorTotal) " +
            "FROM Funcionario f " +
            "JOIN f.vendas v " +
            "GROUP BY f.nome, f.matricula")
    List<Object[]> findTotalVendasByFuncionario();
}
