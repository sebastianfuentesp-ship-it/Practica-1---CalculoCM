
package com.example.calculocm.calculator

data class ResultadoIMC(
    val imc: Double,
    val clasificacion: ClasificacionIMC
)

enum class ClasificacionIMC {
    BAJO_PESO,
    PESO_NORMAL,
    SOBREPESO,
    OBESIDAD
}

enum class ErrorIMC {
    VALORES_NO_POSITIVOS,
    VALORES_FUERA_DE_RANGO
}

class BmiCalculator {

    var ultimoError: ErrorIMC? = null
        private set

    fun calcular(peso: Double, altura: Double): ResultadoIMC? {
        ultimoError = null

        // Validar valores positivos.
        if (peso <= 0.0 || altura <= 0.0) {
            ultimoError = ErrorIMC.VALORES_NO_POSITIVOS
            return null
        }

        // Validar rangos razonables.
        if (peso > 500.0 || altura > 2.5) {
            ultimoError = ErrorIMC.VALORES_FUERA_DE_RANGO
            return null
        }

        // Calcular el IMC.
        val imc = peso / (altura * altura)

        // Clasificar el resultado.
        val clasificacion = when {
            imc < 18.5 -> ClasificacionIMC.BAJO_PESO
            imc < 25.0 -> ClasificacionIMC.PESO_NORMAL
            imc < 30.0 -> ClasificacionIMC.SOBREPESO
            else -> ClasificacionIMC.OBESIDAD
        }

        return ResultadoIMC(
            imc = imc,
            clasificacion = clasificacion
        )
    }
}
