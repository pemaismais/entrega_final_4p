package app.gestao_loja.service;

import app.gestao_loja.entity.Funcionario;
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
}