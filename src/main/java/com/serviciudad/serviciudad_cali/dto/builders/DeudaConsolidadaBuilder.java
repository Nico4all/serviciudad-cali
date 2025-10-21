package com.serviciudad.serviciudad_cali.dto.builders;

import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import com.serviciudad.serviciudad_cali.dto.DetalleServicioDTO;

import java.math.BigDecimal;
import java.time.Instant;

public class DeudaConsolidadaBuilder {
    private final DeudaConsolidadaDTO dto = new DeudaConsolidadaDTO();

    public DeudaConsolidadaBuilder withClienteId(String id) {
        dto.setClienteId(id);
        return this;
    }

    public DeudaConsolidadaBuilder withNombreCliente(String nombre) {
        dto.setNombreCliente(nombre);
        return this;
    }

    public DeudaConsolidadaBuilder withFechaConsulta(Instant fecha) {
        dto.setFechaConsulta(fecha);
        return this;
    }

    public DeudaConsolidadaBuilder withEnergia(DetalleServicioDTO energia) {
        dto.setEnergia(energia);
        return this;
    }

    public DeudaConsolidadaBuilder withAcueducto(DetalleServicioDTO acueducto) {
        dto.setAcueducto(acueducto);
        return this;
    }

    public DeudaConsolidadaBuilder withTotalAPagar(BigDecimal total) {
        dto.setTotalAPagar(total);
        return this;
    }

    public DeudaConsolidadaDTO build() {
        return dto;
    }
}
