
package com.example.calculocm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculocm.R
import com.example.calculocm.model.CalculationFormConfig
import com.example.calculocm.ui.theme.MyBorder
import com.example.calculocm.ui.theme.MyError
import com.example.calculocm.ui.theme.MyErrorBackground
import com.example.calculocm.ui.theme.MyLightGreen
import com.example.calculocm.ui.theme.MySuccessText
import com.example.calculocm.ui.theme.MyWhite

@Composable
fun CalculationForm(
    config: CalculationFormConfig,
    onCalculate: (List<Double>) -> String,
    onBack: () -> Unit
) {
    val values = remember(config) {
        mutableStateListOf<String>().apply {
            repeat(config.fieldCount) {
                add("")
            }
        }
    }

    var result by remember(config) {
        mutableStateOf<String?>(null)
    }

    var error by remember(config) {
        mutableStateOf<String?>(null)
    }

    // Textos traducibles desde strings.xml
    val descripcionDatos =
        stringResource(R.string.descripcion_datos_calculo)

    val etiquetaFormula =
        stringResource(R.string.etiqueta_formula)

    val tituloDatosEntrada =
        stringResource(R.string.titulo_datos_entrada)

    val errorCamposVacios =
        stringResource(R.string.error_campos_vacios)

    val errorNumerosInvalidos =
        stringResource(R.string.error_numeros_invalidos)

    val errorNumerosNoFinitos =
        stringResource(R.string.error_numeros_no_finitos)

    val mensajeErrorCalculo =
        stringResource(R.string.error_calculo)

    val textoCalcular =
        stringResource(R.string.boton_calcular)

    val tituloResultado =
        stringResource(R.string.titulo_resultado)

    val tituloDatosUtilizados =
        stringResource(R.string.titulo_datos_utilizados)

    val tituloErrorCalculo =
        stringResource(R.string.titulo_error_calculo)

    val textoVolverMenu =
        stringResource(R.string.boton_volver_menu)

    // Patrón de validación definido en strings.xml
    val patronNumero = stringResource(R.string.regex_numero_decimal)

    val formatoNumero = remember(patronNumero) {
        Regex(patronNumero)
    }
    // Información específica de cada calculadora
    val informacion = stringResource(config.infoRes)
    val advertencia = stringResource(config.avisoRes)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(config.screenBackground)
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 65.dp,
                bottom = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        // Encabezado del módulo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(16.dp),
                color = config.primaryColor
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = config.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(config.titleRes),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = config.darkColor,
                    lineHeight = 27.sp
                )

                Text(
                    text = descripcionDatos,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Tarjeta informativa
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = config.softColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = config.primaryColor,
                        modifier = Modifier.size(22.dp)
                    )

                    Text(
                        text = informacion,
                        fontSize = 14.sp,
                        color = config.darkColor,
                        lineHeight = 20.sp
                    )
                }

                HorizontalDivider(
                    color = config.primaryColor.copy(alpha = 0.2f)
                )

                Text(
                    text = advertencia,
                    fontSize = 13.sp,
                    color = config.darkColor,
                    lineHeight = 19.sp
                )
            }
        }

        // Tarjeta de fórmula
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MyWhite
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = etiquetaFormula,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = config.primaryColor,
                    letterSpacing = 1.sp
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = config.softColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(config.formulaRes),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = config.darkColor
                    )
                }
            }
        }

        // Formulario de entrada
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MyWhite
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = tituloDatosEntrada,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = config.darkColor
                )

                config.fields.forEachIndexed { index, field ->
                    OutlinedTextField(
                        value = values[index],
                        onValueChange = {
                            values[index] = it
                            result = null
                            error = null
                        },
                        label = {
                            Text(stringResource(field.labelRes))
                        },
                        suffix = {
                            if (field.unit.isNotBlank()) {
                                Text(field.unit)
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = config.primaryColor,
                            focusedLabelColor = config.primaryColor,
                            cursorColor = config.primaryColor,
                            unfocusedBorderColor = MyBorder
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Botón de cálculo y validaciones
                Button(
                    onClick = {
                        // Validar el formato original antes de convertir
                        val numbers = values.map { value ->
                            if (formatoNumero.matches(value)) {
                                value.toDoubleOrNull()
                            } else {
                                null
                            }
                        }

                        when {
                            values.any { it.isBlank() } -> {
                                error = errorCamposVacios
                                result = null
                            }

                            numbers.any { it == null } -> {
                                error = errorNumerosInvalidos
                                result = null
                            }

                            numbers.any { it?.isFinite() != true } -> {
                                error = errorNumerosNoFinitos
                                result = null
                            }

                            else -> {
                                try {
                                    result = onCalculate(
                                        numbers.filterNotNull()
                                    )
                                    error = null
                                } catch (
                                    exception: IllegalArgumentException
                                ) {
                                    error = exception.message
                                        ?: mensajeErrorCalculo
                                    result = null
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = config.primaryColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = textoCalcular,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Resultado correcto
        if (result != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MyLightGreen
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MySuccessText
                        )

                        Text(
                            text = tituloResultado,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MySuccessText
                        )
                    }

                    HorizontalDivider(
                        color = MySuccessText.copy(alpha = 0.2f)
                    )

                    Text(
                        text = result.orEmpty(),
                        fontSize = 15.sp,
                        lineHeight = 23.sp,
                        color = MySuccessText
                    )

                    HorizontalDivider(
                        color = MySuccessText.copy(alpha = 0.2f)
                    )

                    Text(
                        text = tituloDatosUtilizados,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MySuccessText
                    )

                    config.fields.forEachIndexed { index, field ->
                        Text(
                            text = stringResource(
                                R.string.formato_dato_utilizado,
                                stringResource(field.labelRes),
                                values[index]
                            ),
                            fontSize = 14.sp,
                            color = MySuccessText
                        )
                    }
                }
            }
        }

        // Mensaje de error
        if (error != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MyErrorBackground
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = null,
                            tint = MyError
                        )

                        Text(
                            text = tituloErrorCalculo,
                            fontWeight = FontWeight.Bold,
                            color = MyError
                        )
                    }

                    Text(
                        text = error.orEmpty(),
                        color = MyError,
                        lineHeight = 21.sp
                    )
                }
            }
        }

        // Regresar al menú
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MyWhite,
                contentColor = config.primaryColor
            )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(textoVolverMenu)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
