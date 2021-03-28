package br.com.desafio.vacinacao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastraUsuario(@RequestBody @Valid NovoUsuarioRequest request,
                                             UriComponentsBuilder uriComponentsBuilder) {
        //Verifica se já existe usuário cadastrado com o mesmo cpf ou email, pois devem ser únicos
        if (usuarioRepository.existsByCpf(request.getCpf())) {
            return ResponseEntity.status(400).body("Já existe usuário com este cpf cadastrado no sistema!");
        } else if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(400).body("Já existe usuário com este email cadastrado no sistema!");
        }

        Usuario novoUsuario = request.toModel();
        usuarioRepository.save(novoUsuario);

        URI uri = uriComponentsBuilder.path("/api/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.status(201).location(uri).build();

    }

}
