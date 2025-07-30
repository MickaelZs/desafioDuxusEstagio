package br.com.duxusdesafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import br.com.duxusdesafio.repository.ComposicaoTimeRepository;

@RestController
public class ComposicaoTimeController {

    @Autowired
    ComposicaoTimeRepository composicaoTimeRepository;

    
}
