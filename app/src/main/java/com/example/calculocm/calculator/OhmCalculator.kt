
package com.example.calculocm.calculator

data class ResultadoOhm(
    val corriente: Double
)

enum class ErrorOhm {
    VOLTAJE_NEGATIVO,
    RESISTENCIA_NO_POSITIVA,
    VALORES_FUERA_DE_RANGO
}

class OhmCalculator {

    var ultimoError: ErrorOhm? = null
        private set

    fun calcular(voltaje: Double, resistencia: Double): ResultadoOhm? {
        ultimoError = null

        // El voltaje no debe ser negativo.
        if (voltaje < 0.0) {
            ultimoError = ErrorOhm.VOLTAJE_NEGATIVO
            return null
        }

        // La resistencia debe ser mayor que cero.
        if (resistencia <= 0.0) {
            ultimoError = ErrorOhm.RESISTENCIA_NO_POSITIVA
            return null
        }

        // Evitar valores excesivamente grandes.
        if (voltaje > 1_000_000.0 || resistencia > 1_000_000.0) {
            ultimoError = ErrorOhm.VALORES_FUERA_DE_RANGO
            return null
        }

        // Aplicar la ley de Ohm.
        val corriente = voltaje / resistencia

        return ResultadoOhm(
            corriente = corriente
        )
    }
}
