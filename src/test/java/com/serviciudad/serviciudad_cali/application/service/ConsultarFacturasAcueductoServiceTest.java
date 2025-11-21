package com.serviciudad.serviciudad_cali.application.service;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarFacturasAcueductoServiceTest {

    @Test
    void debeRetornarListaDeFacturas() {
        AcueductoRepositoryPort repo = mock(AcueductoRepositoryPort.class);
        ConsultarFacturasAcueductoService service = new ConsultarFacturasAcueductoService(repo);

        String cliente = "987";
        List<FacturaAcueducto> listaMock = List.of(
                new FacturaAcueducto(1L, cliente, "202401", 12, 30000));

        when(repo.obtenerFacturasPorCliente(cliente)).thenReturn(listaMock);

        List<FacturaAcueducto> resultado = service.ejecutar(cliente);

        assertEquals(1, resultado.size());
        assertEquals("202401", resultado.get(0).getPeriodo());
        verify(repo, times(1)).obtenerFacturasPorCliente(cliente);
    }

    @Test
    void debeRetornarListaVaciaCuandoNoHayFacturas() {
        AcueductoRepositoryPort repo = mock(AcueductoRepositoryPort.class);
        ConsultarFacturasAcueductoService service = new ConsultarFacturasAcueductoService(repo);

        when(repo.obtenerFacturasPorCliente("0")).thenReturn(List.of());

        List<FacturaAcueducto> resultado = service.ejecutar("0");

        assertTrue(resultado.isEmpty());
    }
}
