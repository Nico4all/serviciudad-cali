package com.serviciudad.serviciudad_cali.domain.model;

import java.util.List;

public class FacturaUnificada {
    private String idCliente;
    private List<FacturaEnergia> energia;
    private List<FacturaAcueducto> acueducto;

    public FacturaUnificada(String idCliente, List<FacturaEnergia> energia, List<FacturaAcueducto> acueducto) {
        this.idCliente = idCliente;
        this.energia = energia;
        this.acueducto = acueducto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public List<FacturaEnergia> getEnergia() {
        return energia;
    }

    public List<FacturaAcueducto> getAcueducto() {
        return acueducto;
    }
}