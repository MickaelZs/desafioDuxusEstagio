package br.com.duxusdesafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.duxusdesafio.service.ApiService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class IntegranteController {

    @Autowired
    private IntegranteRepository repository;

    @Autowired
    private ApiService apiService;

    @GetMapping("/integrantes")
    public ResponseEntity getall() {
        System.out.println(">>> Resultado da contagem: ");
        List<Integrante> listIntegrantes = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listIntegrantes);
    }

    @PostMapping("/createIntegrante")
    public ResponseEntity create(@RequestBody Integrante integrante) {
        Integrante savedIntegrante = repository.save(integrante);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIntegrante);
    }

    @GetMapping("/integranteMaisUsado")
    public ResponseEntity<Integrante> integranteMaisUsado(

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        System.out.println(">>> Entrou no endpoint integranteMaisUsado");
        List<Time> times = apiService.todosOsTimes();
        Integrante integrante = apiService.integranteMaisUsado(dataInicial, dataFinal, times);

        if (integrante != null) {
            return new ResponseEntity<>(integrante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/funcaoMaisComum")
    public ResponseEntity<String> funcaoMaisComum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> times = apiService.todosOsTimes();
        String funcaoMaisComum = apiService.funcaoMaisComum(dataInicial, dataFinal, times);

        if (funcaoMaisComum != null) {
            return new ResponseEntity<>(funcaoMaisComum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contagemPorFranquia")
    public ResponseEntity<Map<String, Long>> contagemPorFranquia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> times = apiService.todosOsTimes();
        Map<String, Long> contagemFranquias = apiService.contagemPorFranquia(dataInicial, dataFinal, times);

        if (!contagemFranquias.isEmpty()) {
            return new ResponseEntity<>(contagemFranquias, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contagemPorFuncao")
    public ResponseEntity<Map<String, Long>> contagemPorFuncao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        Map<String, Long> contagemFuncoes = apiService.contagemPorFuncao(dataInicial, dataFinal,
                apiService.todosOsTimes());
        return ResponseEntity.ok(contagemFuncoes);
    }

}