package com.serviciudad.serviciudad_cali;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiciudadCaliApplicationTest {

    @Test
    void contextLoads() {
        // Solo ejecuta main para JaCoCo
        ServiciudadCaliApplication.main(new String[]{});
    }
}
