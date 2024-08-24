package app.gestao_loja.service;

import app.gestao_loja.entity.Cliente;
import app.gestao_loja.entity.Funcionario;
import app.gestao_loja.entity.Venda;
import app.gestao_loja.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public Funcionario save(Funcionario funcionario) throws Exception {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> findAll() throws Exception {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) throws Exception {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario com id " + id + " não encontrado"));
    }

    public Funcionario update(Long id, Funcionario funcionario) throws Exception {
        if (funcionarioRepository.existsById(id)) {
            funcionario.setId(id);
            return funcionarioRepository.save(funcionario);
        } else {
            throw new RuntimeException("Funcionario com id " + id + " não encontrado");
        }

    }

    public void delete(Long id) throws Exception {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Funcionario com id " + id + " não encontrado");
        }
    }

    public Funcionario findByMatricula(String matricula) throws Exception{
        Funcionario funcionario = funcionarioRepository.findByMatricula(matricula);
        if(funcionario != null){
            return funcionario;
        }else{
            throw new RuntimeException("Funcionario de matricula: " + matricula + " não encontrado!");
        }
    }

    public Funcionario findByNome(String nome) throws Exception{
        Funcionario funcionario = funcionarioRepository.findByNomeContaining(nome);
        if(funcionario != null){
            return funcionario;
        }else{
            throw new RuntimeException("Funcionario de nome: " + nome + " não encontrado!");
        }
    }

    public List<Object[]> findTotalVendasByFuncionario(){
        List<Object[]> response = funcionarioRepository.findTotalVendasByFuncionario();
        if(!response.isEmpty()){
            return response;
        }else{
            throw new RuntimeException("Nenhuma venda de funcionario encontrada!");
        }
    }
}