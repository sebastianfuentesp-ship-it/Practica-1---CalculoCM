
package com.example.calculocm.calculator

import kotlin.math.PI

data class ResultadoCilindro(
    val volumen: Double
)

enum class ErrorCilindro {
    DIMENSIONES_NO_POSITIVAS,
    DIMENSIONES_FUERA_DE_RANGO
}

class CylinderCalculator {

    var ultimoError: ErrorCilindro? = null
        private set

    fun calcular(radio: Double, altura: Double): ResultadoCilindro? {
        ultimoError = null

        // Validar dimensiones positivas.
        if (radio <= 0.0 || altura <= 0.0) {
            ultimoError = ErrorCilindro.DIMENSIONES_NO_POSITIVAS
            return null
        }

        // Validar dimensiones demasiado grandes.
        if (radio > 1_000_000.0 || altura > 1_000_000.0) {
            ultimoError = ErrorCilindro.DIMENSIONES_FUERA_DE_RANGO
            return null
        }

        // Calcular el volumen.
        val volumen = PI * radio * radio * altura

        return ResultadoCilindro(volumen = volumen)
    }
}
