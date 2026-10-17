package com.example.calculocm.calculator

import kotlin.math.sqrt

data class ResultadoCuadratica(
    val x1: Double,
    val x2: Double,
    val discriminante: Double
)

enum class ErrorCuadratica {
    COEFICIENTE_A_CERO,
    DISCRIMINANTE_NEGATIVO
}

class QuadraticCalculator {

    var ultimoError: ErrorCuadratica? = null
        private set

    var ultimoDiscriminante: Double? = null
        private set

    fun calcular(
        a: Double,
        b: Double,
        c: Double
    ): ResultadoCuadratica? {

        ultimoError = null
        ultimoDiscriminante = null

        if (a == 0.0) {
            ultimoError = ErrorCuadratica.COEFICIENTE_A_CERO
            return null
        }

        val discriminante = b * b - 4 * a * c
        ultimoDiscriminante = discriminante

        if (discriminante < 0.0) {
            ultimoError = ErrorCuadratica.DISCRIMINANTE_NEGATIVO
            return null
        }

        val x1 = (-b + sqrt(discriminante)) / (2 * a)
        val x2 = (-b - sqrt(discriminante)) / (2 * a)

        return ResultadoCuadratica(
            x1 = x1,
            x2 = x2,
            discriminante = discriminante
        )
    }
}