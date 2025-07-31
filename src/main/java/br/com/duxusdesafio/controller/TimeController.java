package br.com.duxusdesafio.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.dto.TimeDataDto;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;

@RestController

public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ApiService apiService;

    @GetMapping("/times")
    public List<Time> todosOsTimes() {
        return timeRepository.findAll();
    }

    @GetMapping("/timeDaData")
    public ResponseEntity<TimeDataDto> timeDaData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<Time> times = apiService.todosOsTimes();
        TimeDataDto time = apiService.timeDaData(data, times);

        if (time != null) {
            return new ResponseEntity<>(time, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/timeMaisComum")
    public ResponseEntity<List<String>> timeMaisComum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        List<Time> times = apiService.todosOsTimes();

        List<String> timeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, times);

        if (!timeMaisComum.isEmpty()) {
            return new ResponseEntity<>(timeMaisComum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    

}