package demo.equifax.api.apidemo.controller;

import demo.equifax.api.apidemo.model.Centro;
import demo.equifax.api.apidemo.repository.CentroRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroController {


    private final CentroRepository centroRepository;

    public CentroController(CentroRepository centroRepository){
        this.centroRepository = centroRepository;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }


    @GetMapping
    public List<Centro> getCentros(){
        return centroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Centro getCentroId(@PathVariable Integer id) {
        return centroRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Centro centro) throws URISyntaxException {
        Centro savedCentro = centroRepository.save(centro);
        return ResponseEntity.created(new URI("/centros/" + savedCentro.getIdCentro())).body(savedCentro);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCentro(@PathVariable Integer id, @RequestBody Centro centro) {
        Centro currentCentro = centroRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCentro.setNombre(centro.getNombre());
        currentCentro.setDireccion(centro.getDireccion());
        currentCentro = centroRepository.save(centro);

        return ResponseEntity.ok(currentCentro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Integer id) {
        centroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
