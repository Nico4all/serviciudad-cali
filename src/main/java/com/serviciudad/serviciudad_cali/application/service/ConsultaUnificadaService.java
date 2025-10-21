package com.serviciudad.serviciudad_cali.application.service;

import com.serviciudad.serviciudad_cali.domain.model.*;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaUnificadaService {

    private final EnergiaRepositoryPort energiaRepo;
    private final AcueductoRepositoryPort acueductoRepo;

    public ConsultaUnificadaService(EnergiaRepositoryPort energiaRepo, AcueductoRepositoryPort acueductoRepo) {
        this.energiaRepo = energiaRepo;
        this.acueductoRepo = acueductoRepo;
    }

    public FacturaUnificada obtenerFacturasCliente(String idCliente) {
        List<FacturaEnergia> energia = energiaRepo.obtenerFacturasPorCliente(idCliente);
        List<FacturaAcueducto> acueducto = acueductoRepo.obtenerFacturasPorCliente(idCliente);
        return new FacturaUnificada(idCliente, energia, acueducto);
    }
}