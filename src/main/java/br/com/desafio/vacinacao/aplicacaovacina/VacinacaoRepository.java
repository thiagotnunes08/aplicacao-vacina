package br.com.desafio.vacinacao.aplicacaovacina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
}
