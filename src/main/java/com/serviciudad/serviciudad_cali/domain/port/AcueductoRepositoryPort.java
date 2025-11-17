package com.serviciudad.serviciudad_cali.domain.port;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import java.util.List;

public interface AcueductoRepositoryPort {
    List<FacturaAcueducto> obtenerFacturasPorCliente(String idCliente);
}
