package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.application.service.ConsultaUnificadaService;
import com.serviciudad.serviciudad_cali.domain.model.FacturaUnificada;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicios")
public class ConsultaUnificadaController {

    private final ConsultaUnificadaService consultaService;

    public ConsultaUnificadaController(ConsultaUnificadaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/{idCliente}")
    public FacturaUnificada obtenerFacturas(@PathVariable String idCliente) {
        return consultaService.obtenerFacturasCliente(idCliente);
    }
}
