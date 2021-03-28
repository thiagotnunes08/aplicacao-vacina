package br.com.desafio.vacinacao.compartilhado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AdviceDTO> validarMethodArgumentNotValid(MethodArgumentNotValidException e) {
        AdviceDTO ad = new AdviceDTO();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ad.setTitulo(fields);
        ad.setDetalhe(fieldMessage);
        ad.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
        ad.setHora(LocalDateTime.now(ZoneId.of("GMT-3")));

        return new ResponseEntity<>(ad, HttpStatus.BAD_REQUEST);
    }

}
