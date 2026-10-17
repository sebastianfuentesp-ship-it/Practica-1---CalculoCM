
package com.example.calculocm.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class CalculationField(
    @StringRes val labelRes: Int,
    val unit: String = ""
)

data class CalculationFormConfig(
    @StringRes val titleRes: Int,
    @StringRes val formulaRes: Int,
    @StringRes val infoRes: Int,
    @StringRes val avisoRes: Int,
    val fields: List<CalculationField>,
    val color: Color,
    val iconRes: Int,
    val screenBackground: Color,
    val softColor: Color,
    val primaryColor: Color,
    val darkColor: Color
) {
    val fieldCount: Int
        get() = fields.size
}
