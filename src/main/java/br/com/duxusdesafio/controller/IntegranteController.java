package br.com.duxusdesafio.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/integrantes")
public class IntegranteController {

   
@GetMapping
   public String ok() {
        return "Ok";
    }

}