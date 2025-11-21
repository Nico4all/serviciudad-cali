package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EnergiaControllerTest {

    @Test
    void testObtenerFacturas() throws Exception {
        EnergiaRepositoryPort repo = mock(EnergiaRepositoryPort.class);

        when(repo.obtenerFacturasPorCliente("CLI001"))
                .thenReturn(List.of(new FacturaEnergia("CLI001","202401",120,30000)));

        EnergiaController controller = new EnergiaController(repo);

        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(get("/energia/CLI001"))
                .andExpect(status().isOk());
    }

    @Test
    void testPing() throws Exception {
        EnergiaController controller = new EnergiaController(null);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(get("/energia/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("Servicio de Energía activo!"));
    }
}
