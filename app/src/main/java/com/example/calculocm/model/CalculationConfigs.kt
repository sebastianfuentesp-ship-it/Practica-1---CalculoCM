
package com.example.calculocm.model
import com.example.calculocm.R
import com.example.calculocm.ui.theme.BmiBackground
import com.example.calculocm.ui.theme.BmiDark
import com.example.calculocm.ui.theme.BmiPrimary
import com.example.calculocm.ui.theme.BmiSoft
import com.example.calculocm.ui.theme.CylinderBackground
import com.example.calculocm.ui.theme.CylinderDark
import com.example.calculocm.ui.theme.CylinderPrimary
import com.example.calculocm.ui.theme.CylinderSoft
import com.example.calculocm.ui.theme.MyLightBlue
import com.example.calculocm.ui.theme.MyLightGreen
import com.example.calculocm.ui.theme.MyOrange
import com.example.calculocm.ui.theme.MyPurple
import com.example.calculocm.ui.theme.OhmBackground
import com.example.calculocm.ui.theme.OhmDark
import com.example.calculocm.ui.theme.OhmPrimary
import com.example.calculocm.ui.theme.OhmSoft
import com.example.calculocm.ui.theme.QuadraticBackground
import com.example.calculocm.ui.theme.QuadraticDark
import com.example.calculocm.ui.theme.QuadraticPrimary
import com.example.calculocm.ui.theme.QuadraticSoft

object CalculationConfigs {

    val quadratic = CalculationFormConfig(
        titleRes = R.string.titulo_cuadratica,
        formulaRes = R.string.formula_cuadratica,
        infoRes = R.string.info_cuadratica,
        avisoRes = R.string.aviso_cuadratica,
        color = MyLightBlue,
        iconRes = R.drawable.ic_cuadratica,
        screenBackground = QuadraticBackground,
        softColor = QuadraticSoft,
        primaryColor = QuadraticPrimary,
        darkColor = QuadraticDark,
        fields = listOf(
            CalculationField(R.string.coeficiente_a),
            CalculationField(R.string.coeficiente_b),
            CalculationField(R.string.coeficiente_c)
        )
    )

    val cylinder = CalculationFormConfig(
        titleRes = R.string.titulo_cilindro,
        formulaRes = R.string.formula_cilindro,
        infoRes = R.string.info_cilindro,
        avisoRes = R.string.aviso_cilindro,
        color = MyLightGreen,
        iconRes = R.drawable.ic_cilindro,
        screenBackground = CylinderBackground,
        softColor = CylinderSoft,
        primaryColor = CylinderPrimary,
        darkColor = CylinderDark,
        fields = listOf(
            CalculationField(R.string.radio),
            CalculationField(R.string.altura)
        )
    )

    val bmi = CalculationFormConfig(
        titleRes = R.string.titulo_imc,
        formulaRes = R.string.formula_imc,
        infoRes = R.string.info_imc,
        avisoRes = R.string.aviso_imc,
        color = MyOrange,
        iconRes = R.drawable.ic_imc,
        screenBackground = BmiBackground,
        softColor = BmiSoft,
        primaryColor = BmiPrimary,
        darkColor = BmiDark,
        fields = listOf(
            CalculationField(R.string.peso),
            CalculationField(R.string.altura_imc)
        )
    )

    val ohm = CalculationFormConfig(
        titleRes = R.string.titulo_ley_ohm,
        formulaRes = R.string.formula_ley_ohm,
        infoRes = R.string.info_ley_ohm,
        avisoRes = R.string.aviso_ley_ohm,
        color = MyPurple,
        iconRes = R.drawable.ic_ohm,
        screenBackground = OhmBackground,
        softColor = OhmSoft,
        primaryColor = OhmPrimary,
        darkColor = OhmDark,
        fields = listOf(
            CalculationField(R.string.voltaje),
            CalculationField(R.string.resistencia)
        )
    )
}
