package com.adamglin.composecontinuousroundedcornershape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

actual fun ContinuousRoundedCornerShape(
    value: Dp,
    smooth: Float
): Shape {
    return RoundedCornerShape(value)
}

actual fun ContinuousRoundedCornerShape(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    smooth: Float
): Shape {
    return RoundedCornerShape(topStart, topEnd, bottomStart, bottomEnd)
}