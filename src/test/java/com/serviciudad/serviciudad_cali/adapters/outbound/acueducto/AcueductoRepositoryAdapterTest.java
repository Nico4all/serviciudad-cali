package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto;

import com.serviciudad.serviciudad_cali.adapters.outbound.acueducto.entity.FacturaAcueductoEntity;
import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AcueductoRepositoryAdapterTest {

    @Test
    void testAdapterConvierteEntitiesADominio() {
        SpringDataAcueductoRepository repo = mock(SpringDataAcueductoRepository.class);
        AcueductoRepositoryAdapter adapter = new AcueductoRepositoryAdapter(repo);

        FacturaAcueductoEntity e = new FacturaAcueductoEntity();
        // Simular getters con spy
        FacturaAcueductoEntity spyEntity = spy(e);
        doReturn(1L).when(spyEntity).getId();
        doReturn("CLI001").when(spyEntity).getIdCliente();
        doReturn("202401").when(spyEntity).getPeriodo();
        doReturn(12).when(spyEntity).getConsumoM3();
        doReturn(20000.0).when(spyEntity).getValorPagar();

        when(repo.findByIdCliente("CLI001")).thenReturn(List.of(spyEntity));

        List<FacturaAcueducto> facturas = adapter.obtenerFacturasPorCliente("CLI001");

        assertEquals(1, facturas.size());
        assertEquals("CLI001", facturas.get(0).getIdCliente());
        assertEquals(12, facturas.get(0).getConsumoM3());
    }
}
