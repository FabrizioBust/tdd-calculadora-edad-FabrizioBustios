package com.calculadora.edad;

import java.time.LocalDate;

public class CalculadoraEdad {

    public static int calcularEdad(LocalDate fechaNacimiento, LocalDate fechaActual) {

        validarFechaNacimiento(fechaNacimiento, fechaActual);

        int edad = fechaActual.getYear() - fechaNacimiento.getYear();

        if (!yaCumplioAnios(fechaNacimiento, fechaActual)) {
            edad--;
        }

        return edad;
    }

    private static void validarFechaNacimiento(
            LocalDate fechaNacimiento,
            LocalDate fechaActual) {

        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalArgumentException(
                "La fecha de nacimiento no puede ser posterior a la fecha actual"
            );
        }
    }

    private static boolean yaCumplioAnios(
            LocalDate fechaNacimiento,
            LocalDate fechaActual) {

        int mesCumple = fechaNacimiento.getMonthValue();
        int diaCumple = fechaNacimiento.getDayOfMonth();

        // Nacidos el 29 de febrero cumplen el 1 de marzo
        // cuando el año actual no es bisiesto.
        if (mesCumple == 2 && diaCumple == 29 && !fechaActual.isLeapYear()) {
            mesCumple = 3;
            diaCumple = 1;
        }

        return fechaActual.getMonthValue() > mesCumple
            || (fechaActual.getMonthValue() == mesCumple
                && fechaActual.getDayOfMonth() >= diaCumple);
    }
}