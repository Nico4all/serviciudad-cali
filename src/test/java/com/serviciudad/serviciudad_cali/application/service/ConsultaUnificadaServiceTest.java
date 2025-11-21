package com.serviciudad.serviciudad_cali.application.service;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultaUnificadaServiceTest {

    private EnergiaRepositoryPort energiaRepo;
    private AcueductoRepositoryPort acueductoRepo;
    private ConsultaUnificadaService service;

    @BeforeEach
    void setUp() {
        energiaRepo = mock(EnergiaRepositoryPort.class);
        acueductoRepo = mock(AcueductoRepositoryPort.class);
        service = new ConsultaUnificadaService(energiaRepo, acueductoRepo);
    }

    @Test
    void debeUnificarFacturasCorrectamente() {
        String cliente = "1234567890";

        when(energiaRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of(
                new FacturaEnergia(cliente, "202401", 120, 50000),
                new FacturaEnergia(cliente, "202402", 140, 60000)
        ));

        when(acueductoRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of(
                new FacturaAcueducto(1L, cliente, "202401", 10, 20000)
        ));

        DeudaConsolidadaDTO dto = service.obtenerFacturasCliente(cliente);

        assertEquals(cliente, dto.getClienteId());
        assertEquals("202402", dto.getEnergia().getPeriodo());
        assertEquals("202401", dto.getAcueducto().getPeriodo());
        assertEquals(80000, dto.getTotalAPagar().intValue());
    }

    @Test
    void debeRetornarValoresCeroCuandoNoHayFacturas() {
        String cliente = "999";

        when(energiaRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of());
        when(acueductoRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of());

        DeudaConsolidadaDTO dto = service.obtenerFacturasCliente(cliente);

        assertEquals("0 kWh", dto.getEnergia().getConsumo());
        assertEquals("0 m³", dto.getAcueducto().getConsumo());
        assertEquals(0, dto.getTotalAPagar().intValue());
    }

    @Test
    void debeTomarFacturasMasRecientesSiEstanDesordenadas() {
        String cliente = "A100";

        when(energiaRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of(
                new FacturaEnergia(cliente, "202312", 100, 30000),
                new FacturaEnergia(cliente, "202401", 120, 35000),
                new FacturaEnergia(cliente, "202310", 90, 20000)
        ));

        when(acueductoRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of(
                new FacturaAcueducto(1L, cliente, "202401", 10, 20000)
        ));

        DeudaConsolidadaDTO dto = service.obtenerFacturasCliente(cliente);

        assertEquals("202401", dto.getEnergia().getPeriodo());
        assertEquals("202401", dto.getAcueducto().getPeriodo());
    }

    @Test
    void verificaInteraccionesConRepositorios() {
        String cliente = "ABC123";

        when(energiaRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of());
        when(acueductoRepo.obtenerFacturasPorCliente(cliente)).thenReturn(List.of());

        service.obtenerFacturasCliente(cliente);

        verify(energiaRepo, times(1)).obtenerFacturasPorCliente(cliente);
        verify(acueductoRepo, times(1)).obtenerFacturasPorCliente(cliente);
    }
}
