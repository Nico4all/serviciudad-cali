package com.serviciudad.serviciudad_cali.domain.model;

public class FacturaEnergia {
    private String idCliente;
    private String periodo;
    private int consumoKwh;
    private double valorPagar;

    public FacturaEnergia(String idCliente, String periodo, int consumoKwh, double valorPagar) {
        this.idCliente = idCliente;
        this.periodo = periodo;
        this.consumoKwh = consumoKwh;
        this.valorPagar = valorPagar;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public int getConsumoKwh() {
        return consumoKwh;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    @Override
    public String toString() {
        return "FacturaEnergia{" +
                "idCliente='" + idCliente + '\'' +
                ", periodo='" + periodo + '\'' +
                ", consumoKwh=" + consumoKwh +
                ", valorPagar=" + valorPagar +
                '}';
    }
}
