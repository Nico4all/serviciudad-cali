package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.application.service.ConsultarFacturasAcueductoService;
import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acueducto")
public class AcueductoController {

    private final ConsultarFacturasAcueductoService service;

    public AcueductoController(ConsultarFacturasAcueductoService service) {
        this.service = service;
    }

    @GetMapping("/{idCliente}")
    public List<FacturaAcueducto> obtenerFacturas(@PathVariable String idCliente) {
        return service.ejecutar(idCliente);
    }
}