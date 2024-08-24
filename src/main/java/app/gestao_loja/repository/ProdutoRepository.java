package app.gestao_loja.repository;

import app.gestao_loja.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    public List<Produto> findByNomeContaining(String nome);
    public List<Produto> findByValorLessThan(double valor);

    @Query("SELECT p FROM Produto p ORDER BY p.valor DESC LIMIT 5 ")
    public List<Produto> findTopMaisCaros();

}
