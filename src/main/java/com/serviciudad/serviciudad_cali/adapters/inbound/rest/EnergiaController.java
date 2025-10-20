package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energia")
@CrossOrigin(origins = "*") // Permite llamadas desde cualquier frontend
public class EnergiaController {

    private final EnergiaRepositoryPort energiaRepository;

    public EnergiaController(EnergiaRepositoryPort energiaRepository) {
        this.energiaRepository = energiaRepository;
    }

    @GetMapping("/{idCliente}")
    public List<FacturaEnergia> obtenerFacturasPorCliente(@PathVariable String idCliente) {
        return energiaRepository.obtenerFacturasPorCliente(idCliente);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Servicio de Energía activo!";
    }
}
