package demo.equifax.api.apidemo.repository;

import demo.equifax.api.apidemo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
