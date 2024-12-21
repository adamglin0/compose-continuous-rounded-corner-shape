package com.adamglin.composesuperroundedcornershape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

actual fun SuperRoundedCornerShape(
    value: Dp,
    smooth: Float
): Shape {
    return RoundedCornerShape(value)
}

actual fun SuperRoundedCornerShape(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    smooth: Float
): Shape {
    return RoundedCornerShape(topStart, topEnd, bottomStart, bottomEnd)
}