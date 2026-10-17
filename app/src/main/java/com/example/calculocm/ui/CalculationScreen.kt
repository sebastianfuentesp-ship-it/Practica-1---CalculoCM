package com.example.calculocm.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.calculocm.R
import com.example.calculocm.model.CalculationType
import com.example.calculocm.model.CalculationConfigs
import com.example.calculocm.ui.components.CalculationForm
import com.example.calculocm.calculator.QuadraticCalculator
import com.example.calculocm.calculator.ErrorCuadratica
import com.example.calculocm.calculator.CylinderCalculator
import com.example.calculocm.calculator.ErrorCilindro
import com.example.calculocm.calculator.BmiCalculator
import com.example.calculocm.calculator.ClasificacionIMC
import com.example.calculocm.calculator.ErrorIMC
import com.example.calculocm.calculator.OhmCalculator
import com.example.calculocm.calculator.ErrorOhm

@Composable
fun CalculationScreen(
    tipo: CalculationType,
    onBack: () -> Unit
) {
    val errorCalculo = stringResource(R.string.error_calculo)

    when (tipo) {

        CalculationType.QUADRATIC -> {
            val calculator = QuadraticCalculator()

            val errorA = stringResource(R.string.error_coeficiente_a_cero)
            val errorRaices = stringResource(R.string.error_sin_raices_reales)
            val etiquetaFormula = stringResource(R.string.etiqueta_formula)
            val etiquetaSustitucion = stringResource(R.string.etiqueta_sustitucion)
            val formatoRaices = stringResource(R.string.resultado_cuadratica)
            val formatoErrorRaices = stringResource(R.string.error_detalle_raices)

            CalculationForm(
                config = CalculationConfigs.quadratic,
                onBack = onBack,
                onCalculate = { values ->
                    val a = values[0]
                    val b = values[1]
                    val c = values[2]

                    val resultado = calculator.calcular(a, b, c)

                    if (resultado == null) {
                        when (calculator.ultimoError) {
                            ErrorCuadratica.COEFICIENTE_A_CERO ->
                                throw IllegalArgumentException(errorA)

                            ErrorCuadratica.DISCRIMINANTE_NEGATIVO -> {
                                val discriminante =
                                    calculator.ultimoDiscriminante ?: throw
                                    IllegalArgumentException(errorCalculo)

                                val detalle = formatoErrorRaices.format(
                                    errorRaices,
                                    etiquetaFormula,
                                    etiquetaSustitucion,
                                    b,
                                    a,
                                    c,
                                    discriminante
                                )

                                throw IllegalArgumentException(detalle)
                            }

                            null -> throw IllegalArgumentException(errorCalculo)
                        }
                    }

                    formatoRaices.format(
                        resultado.x1,
                        resultado.x2,
                        resultado.discriminante
                    )
                }
            )
        }

        CalculationType.CYLINDER -> {
            val calculator = CylinderCalculator()

            val errorDimensiones =
                stringResource(R.string.error_dimensiones_cilindro)
            val errorRango =
                stringResource(R.string.error_rango_cilindro)
            val formatoVolumen =
                stringResource(R.string.resultado_volumen)

            CalculationForm(
                config = CalculationConfigs.cylinder,
                onBack = onBack,
                onCalculate = { values ->
                    val resultado = calculator.calcular(
                        radio = values[0],
                        altura = values[1]
                    )

                    if (resultado == null) {
                        when (calculator.ultimoError) {
                            ErrorCilindro.DIMENSIONES_NO_POSITIVAS ->
                                throw IllegalArgumentException(errorDimensiones)

                            ErrorCilindro.DIMENSIONES_FUERA_DE_RANGO ->
                                throw IllegalArgumentException(errorRango)

                            null -> throw IllegalArgumentException(errorCalculo)
                        }
                    }

                    formatoVolumen.format(resultado.volumen)
                }
            )
        }

        CalculationType.BMI -> {
            val calculator = BmiCalculator()

            val errorValores = stringResource(R.string.error_valores_imc)
            val errorRango = stringResource(R.string.error_rango_imc)
            val formatoIMC = stringResource(R.string.resultado_imc)
            val formatoClasificacion = stringResource(R.string.resultado_clasificacion)

            val textoBajoPeso = stringResource(R.string.imc_bajo_peso)
            val textoPesoNormal = stringResource(R.string.imc_peso_normal)
            val textoSobrepeso = stringResource(R.string.imc_sobrepeso)
            val textoObesidad = stringResource(R.string.imc_obesidad)

            CalculationForm(
                config = CalculationConfigs.bmi,
                onBack = onBack,
                onCalculate = { values ->
                    val resultado = calculator.calcular(
                        peso = values[0],
                        altura = values[1]
                    )

                    if (resultado == null) {
                        when (calculator.ultimoError) {
                            ErrorIMC.VALORES_NO_POSITIVOS ->
                                throw IllegalArgumentException(errorValores)

                            ErrorIMC.VALORES_FUERA_DE_RANGO ->
                                throw IllegalArgumentException(errorRango)

                            null -> throw IllegalArgumentException(errorCalculo)
                        }
                    }

                    val clasificacionTexto = when (resultado.clasificacion) {
                        ClasificacionIMC.BAJO_PESO -> textoBajoPeso
                        ClasificacionIMC.PESO_NORMAL -> textoPesoNormal
                        ClasificacionIMC.SOBREPESO -> textoSobrepeso
                        ClasificacionIMC.OBESIDAD -> textoObesidad
                    }

                    val imcTexto = formatoIMC.format(resultado.imc)

                    formatoClasificacion.format(
                        imcTexto,
                        clasificacionTexto
                    )
                }
            )
        }

        CalculationType.OHM -> {
            val calculator = OhmCalculator()

            val errorVoltaje = stringResource(R.string.error_voltaje_negativo)
            val errorResistencia =
                stringResource(R.string.error_resistencia_no_positiva)
            val errorRango = stringResource(R.string.error_rango_ohm)
            val formatoCorriente = stringResource(R.string.resultado_corriente)

            CalculationForm(
                config = CalculationConfigs.ohm,
                onBack = onBack,
                onCalculate = { values ->
                    val resultado = calculator.calcular(
                        voltaje = values[0],
                        resistencia = values[1]
                    )

                    if (resultado == null) {
                        when (calculator.ultimoError) {
                            ErrorOhm.VOLTAJE_NEGATIVO ->
                                throw IllegalArgumentException(errorVoltaje)

                            ErrorOhm.RESISTENCIA_NO_POSITIVA ->
                                throw IllegalArgumentException(errorResistencia)

                            ErrorOhm.VALORES_FUERA_DE_RANGO ->
                                throw IllegalArgumentException(errorRango)

                            null -> throw IllegalArgumentException(errorCalculo)
                        }
                    }

                    formatoCorriente.format(resultado.corriente)
                }
            )
        }
    }
}