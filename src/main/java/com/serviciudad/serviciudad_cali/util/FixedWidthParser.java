package com.serviciudad.serviciudad_cali.util;

import java.math.BigDecimal;

public final class FixedWidthParser {

    private FixedWidthParser() {
    }

    /**
     * Extrae un campo por posiciones (inicio incluido, fin excluido), con trim.
     */
    public static String field(String line, int startInclusive, int endExclusive) {
        if (line == null)
            return "";
        if (startInclusive >= line.length())
            return "";
        int end = Math.min(endExclusive, line.length());
        return line.substring(startInclusive, end).trim();
    }

    /**
     * Parse BigDecimal.
     */
    public static BigDecimal parseBigDecimal(String s) {
        if (s == null || s.trim().isEmpty())
            return BigDecimal.ZERO;
        String normalized = s.trim().replace(",", "");
        // si contiene punto, ok; si no, intentar interpretar entero/centavos
        try {
            return new BigDecimal(normalized);
        } catch (Exception e) {
            // fallback: quitar no numéricos
            String digits = normalized.replaceAll("[^0-9.-]", "");
            if (digits.isBlank())
                return BigDecimal.ZERO;
            return new BigDecimal(digits);
        }
    }

    public static int parseInt(String s) {
        if (s == null || s.trim().isEmpty())
            return 0;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            String digits = s.replaceAll("\\D", "");
            return digits.isBlank() ? 0 : Integer.parseInt(digits);
        }
    }
}
