package br.com.duxusdesafio.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;

@RestController

public class TimeController {

   @Autowired
    private TimeRepository timeRepository;

    @GetMapping("/times")
    public List<Time> getAllTimes() {
        return timeRepository.findAll();
    }


    @GetMapping("/timeDaData")
    public Time buscarTimeDaData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        LocalDate localDate = data; // Convertendo a data recebida para LocalDate
        // Buscando o time pela data usando o repositório
        return timeRepository.findByData(localDate)
                .orElseThrow(() -> new RuntimeException("Time não encontrado para a data: " + data));
    }

    


  

}