package br.com.duxusdesafio.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.duxusdesafio.model.Integrante;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {

}
