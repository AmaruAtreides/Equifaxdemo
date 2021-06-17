package demo.equifax.api.apidemo.controller;


import demo.equifax.api.apidemo.model.Solicitud;
import demo.equifax.api.apidemo.repository.SolicitudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {


    private final SolicitudRepository solicitudRepository;

    public SolicitudController(SolicitudRepository solicitudRepository){
        this.solicitudRepository = solicitudRepository;
    }

    @GetMapping
    public List<Solicitud> getSolicitudes(){
        return solicitudRepository.findAll();
    }

    @GetMapping("/{id}")
    public Solicitud getSolicitudId(@PathVariable Integer id) {
        return solicitudRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createSolicitud(@RequestBody Solicitud solicitud) throws URISyntaxException {
        Solicitud savedSolicitud = solicitudRepository.save(solicitud);
        return ResponseEntity.created(new URI("/solicitudes/" + savedSolicitud.getIdSolicitud())).body(savedSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSolicitud(@PathVariable Integer id, @RequestBody Solicitud solicitud) {
        Solicitud currentSolicitud = solicitudRepository.findById(id).orElseThrow(RuntimeException::new);
        currentSolicitud.setUsuarioSolicitante(solicitud.getUsuarioSolicitante());
        currentSolicitud.setUsuarioResponsable(solicitud.getUsuarioResponsable());
        currentSolicitud.setProblema(solicitud.getProblema());
        currentSolicitud.setIdCentro(solicitud.getIdCentro());
        currentSolicitud = solicitudRepository.save(solicitud);

        return ResponseEntity.ok(currentSolicitud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSolicitud(@PathVariable Integer id) {
        solicitudRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
