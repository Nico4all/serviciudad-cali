package com.serviciudad.serviciudad_cali.domain.model;

public class FacturaAcueducto {
    private Long id;
    private String idCliente;
    private String periodo;
    private int consumoM3;
    private double valorPagar;

    public FacturaAcueducto(Long id, String idCliente, String periodo, int consumoM3, double valorPagar) {
        this.id = id;
        this.idCliente = idCliente;
        this.periodo = periodo;
        this.consumoM3 = consumoM3;
        this.valorPagar = valorPagar;
    }

    public FacturaAcueducto() {
    }

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

    @Override
    public String toString() {
        return "FacturaAcueducto{" +
                "id=" + id +
                ", idCliente='" + idCliente + '\'' +
                ", periodo='" + periodo + '\'' +
                ", consumoM3=" + consumoM3 +
                ", valorPagar=" + valorPagar +
                '}';
    }
}
