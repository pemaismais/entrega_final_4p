package app.gestao_loja.service;

import app.gestao_loja.entity.*;
import app.gestao_loja.repository.ClienteRepository;
import app.gestao_loja.repository.FuncionarioRepository;
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
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    private void calcularVenda(Venda venda) {
        double total = 0;
        for (ItemProduto itemProduto : venda.getItensProduto()) {
            // Se for colocado id, busca no banco de dados
            if (itemProduto.getIdProduto() != null) {
                Produto produto = produtoRepository
                        .findById(itemProduto.getIdProduto())
                        .get();
                total += produto.getValor() * itemProduto.getQuantidade();
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

    public List<Venda> findByFuncionario(Long id) throws Exception{
        if(funcionarioRepository.existsById(id)){
            Funcionario funcionario = new Funcionario();
            funcionario.setId(id);
            List<Venda> vendas = vendaRepository.findByFuncionario(funcionario);
            if (!vendas.isEmpty()){
                return vendas;
            }else{
                throw new RuntimeException("Funcionario com id " + id + " não tem vendas!");
            }
        }else{
            throw new RuntimeException("Funcionario com id " + id + " não encontrado");
        }
    }

    public List<Venda> findByCliente(Long id) throws Exception{
        if(clienteRepository.existsById(id)){
            Cliente cliente = new Cliente();
            cliente.setId(id);
            List<Venda> response = vendaRepository.findByCliente(cliente) ;
            if(!response.isEmpty()){
                return response;
            }else{
                throw new RuntimeException("Cliente com id " + id + " não tem compras!");
            }
        }else{
            throw new RuntimeException("Cliente com id " + id + " não encontrado");
        }
    }

    public List<Venda> findByVendaMaiorQue(double valor){
        List<Venda> vendas = vendaRepository.findVendaMaiorQue(valor);
        if (!vendas.isEmpty()) {
            return vendas;
        }else{
            throw new RuntimeException("Nenhuma venda de valor maior que: " + valor + " encontrada!");
        }
    }

}
