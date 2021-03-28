package br.com.desafio.vacinacao.usuario;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoUsuarioRequest {

    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;
    @NotBlank(message = "Email deve ser preenchido")
    @Email(message = "Email deve ter formato válido")
    private String email;
    @NotBlank(message = "CPF deve ser preenchido")
    @CPF(message = "CPF deve ter formato válido")
    private String cpf;
    @NotNull(message = "Data de nascimento deve ser preenchida")
    private LocalDate dataNascimento;


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Usuario toModel() {
        return new Usuario(nome, email, cpf, dataNascimento);
    }
}
