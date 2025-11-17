package com.serviciudad.serviciudad_cali.application.service;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarFacturasAcueductoService {

    private final AcueductoRepositoryPort repositoryPort;

    public ConsultarFacturasAcueductoService(AcueductoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public List<FacturaAcueducto> ejecutar(String idCliente) {
        return repositoryPort.obtenerFacturasPorCliente(idCliente);
    }
}
