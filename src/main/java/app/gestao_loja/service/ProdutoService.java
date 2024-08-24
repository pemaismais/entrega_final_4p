package app.gestao_loja.service;

import app.gestao_loja.entity.Produto;
import app.gestao_loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto save(Produto produto) throws Exception {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() throws Exception {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) throws Exception {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com id " + id + " não encontrado"));
    }

    public Produto update(Long id, Produto produto) throws Exception {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto com id " + id + " não encontrado");
        }

    }

    public void delete(Long id) throws Exception {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto com id " + id + " não encontrado");
        }
    }

    public List<Produto> findByNomeContendo(String nome) throws Exception{
        List<Produto> produtos = produtoRepository.findByNomeContaining(nome);
        if(!produtos.isEmpty()){
            return produtos;
        }else{
            throw new RuntimeException("Produto de nome: " + nome + " não encontrado!");
        }
    }
    public List<Produto> findByValorLessThan(double valor) throws Exception{
        List<Produto> produtos = produtoRepository.findByValorLessThan(valor);
        if(!produtos.isEmpty()){
            return produtos;
        }else{
            throw new RuntimeException("Produtos de valor abaixo de: " + valor + " não encontrados");
        }
    }

    public List<Produto> findTopMaisCaros() throws Exception{
        List<Produto> produtos = produtoRepository.findTopMaisCaros();
        if(!produtos.isEmpty()){
            return produtos;
        }else{
            throw new RuntimeException("Produtos não encontrados");
        }
    }

}