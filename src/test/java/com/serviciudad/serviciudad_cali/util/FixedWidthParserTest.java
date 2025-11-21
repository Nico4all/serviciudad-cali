package com.serviciudad.serviciudad_cali.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FixedWidthParserTest {

    @Test
    void fieldDebeRetornarCampoCorrecto() {
        String line = "abcdefghij";
        assertEquals("abc", FixedWidthParser.field(line, 0, 3));
        assertEquals("hij", FixedWidthParser.field(line, 7, 15));
        assertEquals("", FixedWidthParser.field(null, 0, 3));
        assertEquals("", FixedWidthParser.field(line, 20, 25));
    }

    @Test
    void parseBigDecimalDebeFuncionar() {
        assertEquals(BigDecimal.ZERO, FixedWidthParser.parseBigDecimal(null));
        assertEquals(BigDecimal.ZERO, FixedWidthParser.parseBigDecimal(""));
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(FixedWidthParser.parseBigDecimal("1,000")));
        assertEquals(0, BigDecimal.valueOf(1000.50).compareTo(FixedWidthParser.parseBigDecimal("1000.50")));
        assertEquals(0, BigDecimal.valueOf(123).compareTo(FixedWidthParser.parseBigDecimal("abc123")));
    }

    @Test
    void parseIntDebeFuncionar() {
        assertEquals(0, FixedWidthParser.parseInt(null));
        assertEquals(0, FixedWidthParser.parseInt(""));
        assertEquals(123, FixedWidthParser.parseInt("123"));
        assertEquals(1234, FixedWidthParser.parseInt("12ab34"));
        assertEquals(0, FixedWidthParser.parseInt("abc"));
    }
}
