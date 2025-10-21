package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import com.serviciudad.serviciudad_cali.application.service.ConsultaUnificadaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicios")
public class ConsultaUnificadaController {

    private final ConsultaUnificadaService consultaService;

    public ConsultaUnificadaController(ConsultaUnificadaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/{clienteId}")
    public DeudaConsolidadaDTO obtenerFacturas(@PathVariable String clienteId) {
        return consultaService.obtenerFacturasCliente(clienteId);
    }
}
