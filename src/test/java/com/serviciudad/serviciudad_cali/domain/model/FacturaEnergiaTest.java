package com.serviciudad.serviciudad_cali.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FacturaEnergiaTest {

    @Test
    void testConstructorAndGetters() {
        FacturaEnergia factura = new FacturaEnergia("CLI001", "202401", 120, 50000);

        assertEquals("CLI001", factura.getIdCliente());
        assertEquals("202401", factura.getPeriodo());
        assertEquals(120, factura.getConsumoKwh());
        assertEquals(50000, factura.getValorPagar());
    }

    @Test
    void testToString() {
        FacturaEnergia factura = new FacturaEnergia("CLI001", "202401", 120, 50000);
        String text = factura.toString();
        assertTrue(text.contains("CLI001"));
        assertTrue(text.contains("202401"));
        assertTrue(text.contains("50000"));
    }
}
