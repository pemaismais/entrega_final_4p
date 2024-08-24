package app.gestao_loja.controller;

import app.gestao_loja.entity.Venda;
import app.gestao_loja.service.VendaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody  Venda venda){
        try{
            Venda response = vendaService.save(venda);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            List<Venda> response = vendaService.findAll();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Venda response = vendaService.findById(id);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Venda venda){
        try{
            Venda response = vendaService.update(id, venda);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            vendaService.delete(id);
            return ResponseEntity.ok("Venda deletada kk!");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByFuncionario/{funcionarioId}")
    public ResponseEntity<?> getByFuncionario(@PathVariable Long funcionarioId){
        try{
            List<Venda> response = vendaService.findByFuncionario(funcionarioId);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        @GetMapping("/findByCliente/{clienteId}")
    public ResponseEntity<?> getByCliente(@PathVariable Long clienteId){
        try{
            List<Venda> response = vendaService.findByCliente(clienteId);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findVendaMaiorQue/{valor}")
    public ResponseEntity<?> getByVendaMaiorQue(@PathVariable double valor){
        try{
            List<Venda> response = vendaService.findByVendaMaiorQue(valor);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
