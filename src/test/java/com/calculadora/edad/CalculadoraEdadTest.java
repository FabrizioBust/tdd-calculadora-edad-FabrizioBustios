package com.calculadora.edad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CalculadoraEdadTest {

    // PRUEBA 1 - Ya cumplió años este año
    @Test
    void test_edad_cuando_ya_cumplio_anios_este_anio() {
        LocalDate nacimiento = LocalDate.of(2000, 5, 15);
        LocalDate fechaActual = LocalDate.of(2026, 9, 30);

        assertEquals(26, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }

    // PRUEBA 2 - Todavía no cumple años este año
    @Test
    void test_edad_cuando_aun_no_cumple_anios_este_anio() {
        LocalDate nacimiento = LocalDate.of(2000, 12, 10);
        LocalDate fechaActual = LocalDate.of(2026, 9, 30);

        assertEquals(25, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }

    // PRUEBA 3 - Fecha de nacimiento futura
    @Test
    void test_fecha_nacimiento_futura_lanza_excepcion() {
        LocalDate nacimiento = LocalDate.of(2027, 1, 1);
        LocalDate fechaActual = LocalDate.of(2026, 9, 30);

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> CalculadoraEdad.calcularEdad(nacimiento, fechaActual)
        );

        assertEquals(
            "La fecha de nacimiento no puede ser posterior a la fecha actual",
            excepcion.getMessage()
        );
    }

    // PRUEBA 4 - Nacido el 29/02, un día antes de cumplir
    @Test
    void test_bisiesto_un_dia_antes_del_cumpleanios() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate fechaActual = LocalDate.of(2025, 2, 28);

        assertEquals(20, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }

    // PRUEBA 5 - Nacido el 29/02, cumple el 1 de marzo
    @Test
    void test_bisiesto_cumple_el_1_de_marzo() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate fechaActual = LocalDate.of(2025, 3, 1);

        assertEquals(21, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }

    // PRUEBA 6 - Nacido el 29/02 en un año bisiesto
    @Test
    void test_bisiesto_en_anio_bisiesto() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate fechaActual = LocalDate.of(2028, 2, 29);

        assertEquals(24, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }

    // PRUEBA 7 - Día exacto del cumpleaños
    @Test
    void test_edad_el_dia_del_cumpleanios() {
        LocalDate nacimiento = LocalDate.of(2000, 9, 30);
        LocalDate fechaActual = LocalDate.of(2026, 9, 30);

        assertEquals(26, CalculadoraEdad.calcularEdad(nacimiento, fechaActual));
    }
}