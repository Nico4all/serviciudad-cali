package com.serviciudad.serviciudad_cali.application.service;

import com.serviciudad.serviciudad_cali.dto.DetalleServicioDTO;
import com.serviciudad.serviciudad_cali.dto.builders.DeudaConsolidadaBuilder;
import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import com.serviciudad.serviciudad_cali.util.DeudaMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class ConsultaUnificadaService {

    private final EnergiaRepositoryPort energiaRepo;
    private final AcueductoRepositoryPort acueductoRepo;

    public ConsultaUnificadaService(EnergiaRepositoryPort energiaRepo, AcueductoRepositoryPort acueductoRepo) {
        this.energiaRepo = energiaRepo;
        this.acueductoRepo = acueductoRepo;
    }

    public DeudaConsolidadaDTO obtenerFacturasCliente(String clienteId) {
        // obtener listas
        List<FacturaEnergia> energiaList = energiaRepo.obtenerFacturasPorCliente(clienteId);
        List<FacturaAcueducto> acueductoList = acueductoRepo.obtenerFacturasPorCliente(clienteId);

        // tomar la factura más reciente 
        DetalleServicioDTO energiaDTO = energiaList.isEmpty()
                ? new DetalleServicioDTO(null, "0 kWh", BigDecimal.ZERO)
                : DeudaMapper.fromEnergia(energiaList.get(0));

        DetalleServicioDTO acueductoDTO = acueductoList.isEmpty()
                ? new DetalleServicioDTO(null, "0 m³", BigDecimal.ZERO)
                : DeudaMapper.fromAcueducto(acueductoList.get(0));

        BigDecimal total = energiaDTO.getValorPagar().add(acueductoDTO.getValorPagar());

        DeudaConsolidadaDTO dto = new DeudaConsolidadaBuilder()
                .withClienteId(clienteId)
                .withNombreCliente("Juan Pérez") 
                .withFechaConsulta(Instant.now())
                .withEnergia(energiaDTO)
                .withAcueducto(acueductoDTO)
                .withTotalAPagar(total)
                .build();

        return dto;
    }
}
