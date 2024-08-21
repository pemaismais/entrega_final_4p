package app.gestao_loja.service;

import app.gestao_loja.entity.ItemProduto;
import app.gestao_loja.entity.Produto;
import app.gestao_loja.entity.Venda;
import app.gestao_loja.repository.ProdutoRepository;
import app.gestao_loja.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private void calcularVenda(Venda venda) {
        double total = 0;
        for (ItemProduto itemProduto : venda.getItensProduto()) {
            // Se for colocado id, busca no banco de dados
            if (itemProduto.getIdProduto() != null) {
                Produto produto = produtoRepository
                        .findById(itemProduto.getIdProduto())
                        .get();
                total += produto.getValor() * itemProduto.getQuantidade();
            } else { // se nao for colocado id pega direto do produto
                total += itemProduto.getValorProduto();
            }

        }
        venda.setValorTotal(total);
    }

    private void validarVenda(Venda venda) {
        for (ItemProduto itemProduto : venda.getItensProduto()) {
            if (itemProduto.getIdProduto() != null) { // Se colocar id
                 // verificar se existe no banco.
                if (!produtoRepository.existsById(itemProduto.getIdProduto()) ) {
                    throw new RuntimeException("Produto de id: " + itemProduto.getIdProduto() + " não encontrado!");
                }
            } else { // se nao colocar id
                // verificar valor valido
                if (itemProduto.getValorProduto() <= 0) {
                    throw new RuntimeException("Valor de produto invalido!");
                }

            }
            // Vinculando os Items com a Venda
            itemProduto.setVenda(venda);
        }
        calcularVenda(venda);
    }

    public Venda save(Venda venda) throws Exception {
        validarVenda(venda);
        return vendaRepository.save(venda);
    }

    public List<Venda> findAll() throws Exception {
        return vendaRepository.findAll();
    }

    public Venda findById(Long id) throws Exception {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda com id " + id + " não encontrado"));
    }

    public Venda update(Long id, Venda venda) throws Exception {
        if (vendaRepository.existsById(id)) {
            validarVenda(venda);
            venda.setId(id);
            return vendaRepository.save(venda);
        } else {
            throw new RuntimeException("Venda com id " + id + " não encontrado");
        }


    }

    public void delete(Long id) throws Exception {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Venda com id " + id + " não encontrado");
        }
    }
}
