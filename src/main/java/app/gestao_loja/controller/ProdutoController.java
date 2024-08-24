package app.gestao_loja.controller;

import app.gestao_loja.entity.Produto;
import app.gestao_loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Produto produto){
        try{
            Produto response = produtoService.save(produto);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            List<Produto> response = produtoService.findAll();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Produto response = produtoService.findById(id);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Produto produto){
        try{
            Produto response = produtoService.update(id, produto);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            produtoService.delete(id);
            return ResponseEntity.ok("Produto deletada kk!");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getValorMenorQue/{valor}")
    public ResponseEntity<?> getValorLessThan(@PathVariable double valor){
        try{
            List<Produto> response = produtoService.findByValorLessThan(valor);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<?> getByNome(@PathVariable String nome){
        try{
            List<Produto> response = produtoService.findByNomeContendo(nome);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/topMaisCaros")
    public ResponseEntity<?> getTopMaisCaros(){
        try{
            List<Produto> response = produtoService.findTopMaisCaros();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}