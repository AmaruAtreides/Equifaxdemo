package demo.equifax.api.apidemo.controller;

import demo.equifax.api.apidemo.model.Usuario;
import demo.equifax.api.apidemo.repository.UsuarioRepository;
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
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }


    @GetMapping("/{id}")
    public Usuario getUsuarioId(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Usuario usuario) throws URISyntaxException {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.created(new URI("/usuarios/" + savedUsuario.getIdUsuario())).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario currentUsuario = usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUsuario.setNombre(usuario.getNombre());
        currentUsuario.setDescripcion(usuario.getDescripcion());
        currentUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(currentUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
