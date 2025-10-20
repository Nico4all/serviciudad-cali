package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "🚀 Serviciudad Cali API funcionando correctamente!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong (you found an easter egg >:pp)";
    }
}
