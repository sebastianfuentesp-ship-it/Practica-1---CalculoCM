
package com.example.calculocm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculocm.model.CalculationConfigs
import com.example.calculocm.model.CalculationFormConfig
import com.example.calculocm.model.CalculationType
import com.example.calculocm.ui.CalculationScreen
import com.example.calculocm.ui.theme.CalculoCMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculoCMTheme {
                CalculadoraApp()
            }
        }
    }
}

@Composable
fun CalculadoraApp() {
    var calculoSeleccionado by remember {
        mutableStateOf<CalculationType?>(null)
    }

    if (calculoSeleccionado == null) {
        PantallaInicio(
            alSeleccionar = {
                calculoSeleccionado = it
            }
        )
    } else {
        key(calculoSeleccionado) {
            CalculationScreen(
                tipo = calculoSeleccionado!!,
                onBack = {
                    calculoSeleccionado = null
                }
            )
        }
    }
}

@Composable
fun PantallaInicio(
    alSeleccionar: (CalculationType) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.fondo_vertical),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = stringResource(R.string.app_slogan),
                modifier = Modifier.padding(top = 6.dp),
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TarjetaCalculo(
                    config = CalculationConfigs.quadratic,
                    modifier = Modifier.weight(1f)
                ) {
                    alSeleccionar(CalculationType.QUADRATIC)
                }

                TarjetaCalculo(
                    config = CalculationConfigs.cylinder,
                    modifier = Modifier.weight(1f)
                ) {
                    alSeleccionar(CalculationType.CYLINDER)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TarjetaCalculo(
                    config = CalculationConfigs.bmi,
                    modifier = Modifier.weight(1f)
                ) {
                    alSeleccionar(CalculationType.BMI)
                }

                TarjetaCalculo(
                    config = CalculationConfigs.ohm,
                    modifier = Modifier.weight(1f)
                ) {
                    alSeleccionar(CalculationType.OHM)
                }
            }
        }
    }
}

@Composable
fun TarjetaCalculo(
    config: CalculationFormConfig,
    modifier: Modifier = Modifier,
    alHacerClick: () -> Unit
) {
    Column(
        modifier = modifier
            .height(160.dp)
            .background(
                color = config.color,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                alHacerClick()
            }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = config.iconRes),
            contentDescription = null,
            modifier = Modifier.height(56.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(config.formulaRes),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = config.primaryColor,
            textAlign = TextAlign.Center,
            lineHeight = 21.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(config.titleRes),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = config.darkColor,
            textAlign = TextAlign.Center,
            lineHeight = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculoCMTheme {
        PantallaInicio(
            alSeleccionar = {}
        )
    }
}
