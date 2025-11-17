package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto;

import com.serviciudad.serviciudad_cali.domain.model.FacturaAcueducto;
import com.serviciudad.serviciudad_cali.domain.port.AcueductoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AcueductoRepositoryAdapter implements AcueductoRepositoryPort {

    private final SpringDataAcueductoRepository repository;

    @Autowired
    public AcueductoRepositoryAdapter(SpringDataAcueductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FacturaAcueducto> obtenerFacturasPorCliente(String idCliente) {
        return repository.findByIdCliente(idCliente)
                .stream()
                .map(entity -> new FacturaAcueducto(
                        entity.getId(),
                        entity.getIdCliente(),
                        entity.getPeriodo(),
                        entity.getConsumoM3(),
                        entity.getValorPagar()))
                .collect(Collectors.toList());
    }
}
