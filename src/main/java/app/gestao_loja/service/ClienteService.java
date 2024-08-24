package app.gestao_loja.service;

import app.gestao_loja.entity.Cliente;
import app.gestao_loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) throws Exception {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() throws Exception {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) throws Exception {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com id " + id + " não encontrado"));
    }

    public Cliente update(Long id, Cliente cliente) throws Exception {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente com id " + id + " não encontrado");
        }

    }

    public void delete(Long id) throws Exception {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente com id " + id + " não encontrado");
        }
    }
    public Cliente findByTelefone(String telefone) throws Exception {
        Cliente cliente = clienteRepository.findByTelefone(telefone);
        if(cliente != null){
            return cliente;
        }else{
            throw new RuntimeException("Cliente com telefone " + telefone + " não encontrado!");
        }
    }
    public Cliente findByCpf(String cpf) throws Exception {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if(cliente != null){
            return cliente;
        }else{
            throw new RuntimeException("Cliente com cpf " + cpf + " não encontrado!");
        }
    }
    public List<Cliente> findAdultos() throws Exception {
        List<Cliente> clientes = clienteRepository.findAdultos();
        if(!clientes.isEmpty()){
            return clientes;
        }else{
            throw new RuntimeException("Nenhum maior de idade encontrado!");
        }
    }
}
