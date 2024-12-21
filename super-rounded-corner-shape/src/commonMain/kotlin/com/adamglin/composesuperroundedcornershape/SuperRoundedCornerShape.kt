package com.adamglin.composesuperroundedcornershape

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

expect fun SuperRoundedCornerShape(value: Dp, smooth: Float = 0.6f): Shape

expect fun SuperRoundedCornerShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    smooth: Float = 0.6f
): Shape