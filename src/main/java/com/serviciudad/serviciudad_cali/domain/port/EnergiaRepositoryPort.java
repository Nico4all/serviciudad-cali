package com.serviciudad.serviciudad_cali.domain.port;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import java.util.List;

public interface EnergiaRepositoryPort {
    List<FacturaEnergia> obtenerFacturasPorCliente(String idCliente);
}