package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto;

import com.serviciudad.serviciudad_cali.adapters.outbound.acueducto.entity.FacturaAcueductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataAcueductoRepository extends JpaRepository<FacturaAcueductoEntity, Long> {
    List<FacturaAcueductoEntity> findByIdCliente(String idCliente);
}
