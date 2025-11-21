package com.serviciudad.serviciudad_cali.adapters.outbound.energia;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdaptadorArchivoEnergiaTest {

    @Test
    void testObtenerFacturasPorCliente() {
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia();

        List<FacturaEnergia> facturas = adapter.obtenerFacturasPorCliente("0001234567");

        assertEquals(2, facturas.size()); 

        FacturaEnergia f1 = facturas.get(0);
        assertEquals("0001234567", f1.getIdCliente());
        assertEquals("202510", f1.getPeriodo());
        assertEquals(1500, f1.getConsumoKwh());
        assertEquals(180000.50, f1.getValorPagar());

        FacturaEnergia f2 = facturas.get(1);
        assertEquals("0001234567", f2.getIdCliente());
        assertEquals("202509", f2.getPeriodo());
        assertEquals(1250, f2.getConsumoKwh());
        assertEquals(150000.25, f2.getValorPagar());
    }

    @Test
    void testClienteSinFacturas() {
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia();

        List<FacturaEnergia> facturas = adapter.obtenerFacturasPorCliente("NOEXISTE");

        assertTrue(facturas.isEmpty());
    }
}
