package br.com.desafio.vacinacao.aplicacaovacina;

import br.com.desafio.vacinacao.usuario.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaVacinacaoRequest {

    @NotBlank(message = "Nome da Vacina deve ser preenchido")
    private String nomeVacina;
    @NotBlank(message = "Email deve ser preenchido")
    @Email(message = "Email deve conter um formato válido")
    private String emailUsuario;

    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public Vacinacao toModel(Usuario usuario) {
        return new Vacinacao(nomeVacina, usuario);
    }
}
