package com.openbootcamp.springREST.controller.ejercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/saludo")
    public String saludo(){
        return "Hola desde mi rest controller.";
    }
}
