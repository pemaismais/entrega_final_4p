package app.gestao_loja.controller;

import app.gestao_loja.entity.Funcionario;
import app.gestao_loja.entity.Venda;
import app.gestao_loja.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Funcionario funcionario){
        try{
            Funcionario response = funcionarioService.save(funcionario);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            List<Funcionario> response = funcionarioService.findAll();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Funcionario response = funcionarioService.findById(id);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Funcionario funcionario){
        try{
            Funcionario response = funcionarioService.update(id, funcionario);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            funcionarioService.delete(id);
            return ResponseEntity.ok("Funcionario deletada kk!");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByMatricula/{matricula}")
    public ResponseEntity<?> getByMatricula(@PathVariable String matricula){
        try{
            Funcionario response = funcionarioService.findByMatricula(matricula);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByNome/{nome}")
    public ResponseEntity<?> getByNome(@PathVariable String nome){
        try{
            Funcionario response = funcionarioService.findByNome(nome);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findTotalVendasByFuncionario")
    public ResponseEntity<?> getTotalVendasByFuncionario(){
        try{
            List<Object[]> response = funcionarioService.findTotalVendasByFuncionario();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}