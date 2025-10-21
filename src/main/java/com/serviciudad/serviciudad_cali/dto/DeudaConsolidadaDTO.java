package com.serviciudad.serviciudad_cali.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class DeudaConsolidadaDTO {
    private String clienteId;
    private String nombreCliente;
    private Instant fechaConsulta;
    private DetalleServicioDTO energia;
    private DetalleServicioDTO acueducto;
    private BigDecimal totalAPagar;

    public DeudaConsolidadaDTO() {
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Instant getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Instant fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public DetalleServicioDTO getEnergia() {
        return energia;
    }

    public void setEnergia(DetalleServicioDTO energia) {
        this.energia = energia;
    }

    public DetalleServicioDTO getAcueducto() {
        return acueducto;
    }

    public void setAcueducto(DetalleServicioDTO acueducto) {
        this.acueducto = acueducto;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}
