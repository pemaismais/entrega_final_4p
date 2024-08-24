package app.gestao_loja.repository;

import app.gestao_loja.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public Cliente findByTelefone(String telefone);
    public Cliente findByCpf(String cpf);
    @Query("SELECT c FROM Cliente c WHERE c.idade >= 18")
    public List<Cliente> findAdultos();
}
