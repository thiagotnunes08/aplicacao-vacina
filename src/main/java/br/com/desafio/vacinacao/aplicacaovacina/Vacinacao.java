package br.com.desafio.vacinacao.aplicacaovacina;

import br.com.desafio.vacinacao.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeVacina;
    @ManyToOne
    private Usuario usuario;
    @Column(updatable = false)
    private final LocalDateTime dataVacinacao = LocalDateTime.now();

    public Vacinacao(String nomeVacina, Usuario usuario) {
        this.nomeVacina = nomeVacina;
        this.usuario = usuario;
    }

    @Deprecated
    public Vacinacao() {
    }

    public Long getId() {
        return id;
    }
}
