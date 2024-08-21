package app.gestao_loja.repository;

import app.gestao_loja.entity.ItemProduto;
import app.gestao_loja.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto,Long> {
}

