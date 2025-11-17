package com.serviciudad.serviciudad_cali.util;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.dto.DetalleServicioDTO;

import java.math.BigDecimal;

public final class DeudaMapper {

    private DeudaMapper(){}

    public static DetalleServicioDTO fromEnergia(FacturaEnergia f) {
        if (f == null) return new DetalleServicioDTO(null, "0 kWh", BigDecimal.ZERO);
        String consumo = f.getConsumoKwh() + " kWh";
        BigDecimal valor = BigDecimal.valueOf(f.getValorPagar());
        return new DetalleServicioDTO(f.getPeriodo(), consumo, valor);
    }

    public static DetalleServicioDTO fromAcueducto(FacturaAcueducto f) {
        if (f == null) return new DetalleServicioDTO(null, "0 m³", BigDecimal.ZERO);
        String consumo = f.getConsumoM3() + " m³";
        BigDecimal valor = BigDecimal.valueOf(f.getValorPagar());
        return new DetalleServicioDTO(f.getPeriodo(), consumo, valor);
    }
}
