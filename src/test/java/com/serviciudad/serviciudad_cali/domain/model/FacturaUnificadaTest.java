package com.serviciudad.serviciudad_cali.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacturaUnificadaTest {

    @Test
    void testConstructorAndGetters() {
        FacturaEnergia e1 = new FacturaEnergia("CLI001", "202401", 100, 30000);
        FacturaAcueducto a1 = new FacturaAcueducto(1L, "CLI001", "202401", 10, 15000);

        FacturaUnificada fu = new FacturaUnificada("CLI001", List.of(e1), List.of(a1));

        assertEquals("CLI001", fu.getIdCliente());
        assertEquals(1, fu.getEnergia().size());
        assertEquals(1, fu.getAcueducto().size());
    }
}
