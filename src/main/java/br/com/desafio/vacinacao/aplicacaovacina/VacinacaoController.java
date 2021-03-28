package br.com.desafio.vacinacao.aplicacaovacina;

import br.com.desafio.vacinacao.usuario.Usuario;
import br.com.desafio.vacinacao.usuario.UsuarioRepository;
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
@RequestMapping("/api/vacinacao")
public class VacinacaoController {

    @Autowired
    private VacinacaoRepository vacinacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastraVacinacao(@RequestBody @Valid NovaVacinacaoRequest request,
                                               UriComponentsBuilder uriComponentsBuilder) {
        //Verifica se o usuário existe, pois só podemos cadastrar vacinação para usuário cadastrado
        if (!usuarioRepository.existsByEmail(request.getEmailUsuario())) {
            return ResponseEntity.status(400).body("Usuario não encontrado para esse email");
        }
        Usuario usuario = usuarioRepository.findByEmail(request.getEmailUsuario());

        Vacinacao novaVacinacao = request.toModel(usuario);
        vacinacaoRepository.save(novaVacinacao);

        URI uri = uriComponentsBuilder.path("/api/vacinacao/{id}").buildAndExpand(novaVacinacao.getId()).toUri();

        return ResponseEntity.status(201).location(uri).build();
    }
}
