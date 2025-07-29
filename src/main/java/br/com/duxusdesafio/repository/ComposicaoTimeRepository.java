package br.com.duxusdesafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxusdesafio.model.ComposicaoTime;

public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
    // Additional query methods can be defined here if needed
}
