package com.serviciudad.serviciudad_cali.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FacturaAcueductoTest {

    @Test
    void testConstructorAndGetters() {
        FacturaAcueducto factura = new FacturaAcueducto(1L, "CLI001", "202401", 12, 15000);

        assertEquals(1L, factura.getId());
        assertEquals("CLI001", factura.getIdCliente());
        assertEquals("202401", factura.getPeriodo());
        assertEquals(12, factura.getConsumoM3());
        assertEquals(15000, factura.getValorPagar());
    }

    @Test
    void testToString() {
        FacturaAcueducto factura = new FacturaAcueducto(1L, "CLI001", "202401", 12, 15000);
        String text = factura.toString();
        assertTrue(text.contains("CLI001"));
        assertTrue(text.contains("202401"));
        assertTrue(text.contains("15000"));
    }
}
