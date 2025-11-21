package com.serviciudad.serviciudad_cali.dto.builders;

import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import com.serviciudad.serviciudad_cali.dto.DetalleServicioDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DeudaConsolidadaBuilderTest {

    @Test
    void debeConstruirDTOCorrectamente() {
        Instant ahora = Instant.now();

        DetalleServicioDTO energia = new DetalleServicioDTO("202401", "120 kWh", BigDecimal.valueOf(50000));
        DetalleServicioDTO acueducto = new DetalleServicioDTO("202401", "15 m³", BigDecimal.valueOf(20000));

        DeudaConsolidadaDTO dto = new DeudaConsolidadaBuilder()
                .withClienteId("123")
                .withNombreCliente("Juan Pérez")
                .withFechaConsulta(ahora)
                .withEnergia(energia)
                .withAcueducto(acueducto)
                .withTotalAPagar(BigDecimal.valueOf(70000))
                .build();

        assertEquals("123", dto.getClienteId());
        assertEquals("Juan Pérez", dto.getNombreCliente());
        assertEquals(ahora, dto.getFechaConsulta());
        assertEquals(energia, dto.getEnergia());
        assertEquals(acueducto, dto.getAcueducto());
        assertEquals(BigDecimal.valueOf(70000), dto.getTotalAPagar());
    }
}
