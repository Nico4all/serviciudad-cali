package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SpringDataAcueductoRepositoryTest {

    @Test
    void testInterfaceExists() {
        SpringDataAcueductoRepository repo = mock(SpringDataAcueductoRepository.class);
        assertNotNull(repo);
    }
}
