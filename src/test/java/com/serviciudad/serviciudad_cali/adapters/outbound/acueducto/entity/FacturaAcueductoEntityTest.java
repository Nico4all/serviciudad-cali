package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacturaAcueductoEntityTest {

    @Test
    void gettersDebeRetornarValores() {
        FacturaAcueductoEntity entity = new FacturaAcueductoEntity();

        // Como no hay setters, comprobamos que getters no lancen excepciones
        assertNull(entity.getId());
        assertNull(entity.getIdCliente());
        assertNull(entity.getPeriodo());
        assertEquals(0, entity.getConsumoM3());
        assertEquals(0.0, entity.getValorPagar());
    }
}
