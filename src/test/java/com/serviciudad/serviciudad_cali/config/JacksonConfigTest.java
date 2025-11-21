package com.serviciudad.serviciudad_cali.config;

import com.fasterxml.jackson.databind.Module;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JacksonConfigTest {

    @Test
    void javaTimeModuleDebeInstanciar() {
        JacksonConfig config = new JacksonConfig();
        Module module = config.javaTimeModule();
        assertNotNull(module);
    }
}
