package br.com.duxusdesafio.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;

import java.util.List;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/integrantes")
public class IntegranteController {


    @Autowired
    IntegranteRepository repository;

   
@GetMapping
   public ResponseEntity getall() {
    List <Integrante> listIntegrantes = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listIntegrantes) ;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Integrante integrante) {
        Integrante savedIntegrante = repository.save(integrante);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIntegrante);
    }   

}