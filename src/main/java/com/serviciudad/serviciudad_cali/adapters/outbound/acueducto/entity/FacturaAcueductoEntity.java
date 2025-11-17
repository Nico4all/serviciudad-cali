package com.serviciudad.serviciudad_cali.adapters.outbound.acueducto.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas_acueducto")
public class FacturaAcueductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private String idCliente;

    @Column(name = "periodo")
    private String periodo;

    @Column(name = "consumo_m3")
    private int consumoM3;

    @Column(name = "valor_pagar")
    private double valorPagar;

    public Long getId() {
        return id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public int getConsumoM3() {
        return consumoM3;
    }

    public double getValorPagar() {
        return valorPagar;
    }
}
