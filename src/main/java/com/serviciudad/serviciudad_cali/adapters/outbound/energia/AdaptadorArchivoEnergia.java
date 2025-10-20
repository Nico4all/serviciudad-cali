package com.serviciudad.serviciudad_cali.adapters.outbound.energia;

import com.serviciudad.serviciudad_cali.domain.model.FacturaEnergia;
import com.serviciudad.serviciudad_cali.domain.port.EnergiaRepositoryPort;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdaptadorArchivoEnergia implements EnergiaRepositoryPort {
    private static final String ARCHIVO_CONSUMOS = "/data/consumos_energia.txt";

    @Override
    public List<FacturaEnergia> obtenerFacturasPorCliente(String idCliente) {
        List<FacturaEnergia> facturas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(ARCHIVO_CONSUMOS)))) {

            facturas = reader.lines()
                    .map(this::parseLinea)
                    .filter(f -> f.getIdCliente().equals(idCliente))
                    .collect(Collectors.toList());

        } catch (IOException | NullPointerException e) {
            System.err.println("Error leyendo archivo de energía: " + e.getMessage());
        }

        return facturas;
    }

    private FacturaEnergia parseLinea(String linea) {
        try {
            String idCliente = linea.substring(0, 10);
            String periodo = linea.substring(10, 16);
            int consumoKwh = Integer.parseInt(linea.substring(16, 24));
            double valorPagar = Double.parseDouble(linea.substring(24).trim());
            return new FacturaEnergia(idCliente, periodo, consumoKwh, valorPagar);
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear línea: " + linea, e);
        }
    }
}
