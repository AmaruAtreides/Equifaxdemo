package demo.equifax.api.apidemo.repository;

import demo.equifax.api.apidemo.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
}
