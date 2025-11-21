package com.serviciudad.serviciudad_cali.util;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.dto.DetalleServicioDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DeudaMapperTest {

    @Test
    void fromEnergiaDebeMapearCorrectamente() {
        FacturaEnergia f = new FacturaEnergia("123", "202401", 150, 45000);

        DetalleServicioDTO dto = DeudaMapper.fromEnergia(f);

        assertEquals("202401", dto.getPeriodo());
        assertEquals("150 kWh", dto.getConsumo());
        assertEquals(BigDecimal.valueOf(45000.0), dto.getValorPagar());
    }

    @Test
    void fromEnergiaDebeRetornarCerosSiEsNull() {
        DetalleServicioDTO dto = DeudaMapper.fromEnergia(null);

        assertEquals(BigDecimal.ZERO, dto.getValorPagar());
        assertEquals("0 kWh", dto.getConsumo());
    }

    @Test
    void fromAcueductoDebeMapearCorrectamente() {
        FacturaAcueducto f = new FacturaAcueducto(1L, "123", "202401", 10, 20000);

        DetalleServicioDTO dto = DeudaMapper.fromAcueducto(f);

        assertEquals("202401", dto.getPeriodo());
        assertEquals("10 m³", dto.getConsumo());
        assertEquals(BigDecimal.valueOf(20000.0), dto.getValorPagar());
    }

    @Test
    void fromAcueductoDebeRetornarCerosSiEsNull() {
        DetalleServicioDTO dto = DeudaMapper.fromAcueducto(null);

        assertEquals(BigDecimal.ZERO, dto.getValorPagar());
        assertEquals("0 m³", dto.getConsumo());
    }
}
