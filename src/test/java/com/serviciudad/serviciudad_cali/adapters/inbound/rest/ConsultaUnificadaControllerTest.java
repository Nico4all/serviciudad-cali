package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.dto.DeudaConsolidadaDTO;
import com.serviciudad.serviciudad_cali.application.service.ConsultaUnificadaService;

import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConsultaUnificadaControllerTest {

    @Test
    void testObtenerFacturas() throws Exception {
        ConsultaUnificadaService service = mock(ConsultaUnificadaService.class);

        when(service.obtenerFacturasCliente("CLI001"))
                .thenReturn(new DeudaConsolidadaDTO());

        ConsultaUnificadaController controller = new ConsultaUnificadaController(service);

        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(get("/servicios/CLI001"))
                .andExpect(status().isOk());
    }
}
