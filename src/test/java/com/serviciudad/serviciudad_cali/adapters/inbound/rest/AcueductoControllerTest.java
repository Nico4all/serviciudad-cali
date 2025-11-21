package com.serviciudad.serviciudad_cali.adapters.inbound.rest;

import com.serviciudad.serviciudad_cali.application.service.ConsultarFacturasAcueductoService;
import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AcueductoControllerTest {

    @Test
    void testObtenerFacturas() throws Exception {
        ConsultarFacturasAcueductoService service = mock(ConsultarFacturasAcueductoService.class);
        AcueductoController controller = new AcueductoController(service);

        when(service.ejecutar("CLI001")).thenReturn(List.of(
                new FacturaAcueducto(1L, "CLI001", "202401", 10, 15000)
        ));

        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(get("/acueducto/CLI001"))
                .andExpect(status().isOk());
    }

    @Test
    void testPing() throws Exception {
        AcueductoController controller = new AcueductoController(null);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(get("/acueducto/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("Servicio de Acueducto activo!"));
    }
}
