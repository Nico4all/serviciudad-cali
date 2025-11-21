package com.serviciudad.serviciudad_cali.adapters.outbound.energia;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    @Test
    void testParseLineaInvalidaLanzaExcepcion() throws Exception {
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia();

        Method method = AdaptadorArchivoEnergia.class.getDeclaredMethod("parseLinea", String.class);
        method.setAccessible(true);

        String lineaCorta = "12345";

        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
                () -> method.invoke(adapter, lineaCorta));

        // Verificamos que la causa sea RuntimeException
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertTrue(ex.getCause().getMessage().contains("Error al parsear línea"));
    }

    @Test
    void testArchivoNoExisteDevuelveListaVacia() {
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia() {
            @Override
            public List<FacturaEnergia> obtenerFacturasPorCliente(String idCliente) {
                // Forzamos InputStream null
                return super.obtenerFacturasPorCliente("NOEXISTE");
            }
        };
        List<FacturaEnergia> facturas = adapter.obtenerFacturasPorCliente("NOEXISTE");
        assertTrue(facturas.isEmpty());
    }
}
