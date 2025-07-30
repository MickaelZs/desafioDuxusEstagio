package br.com.duxusdesafio.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxusdesafio.model.ComposicaoTime;

public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
   
}
