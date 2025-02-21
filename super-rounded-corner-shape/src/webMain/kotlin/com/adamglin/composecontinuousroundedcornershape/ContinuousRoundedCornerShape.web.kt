@file:Suppress("FunctionName")

package com.adamglin.composecontinuousroundedcornershape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape

/**
 * The expected shape is a rectangle with smooth rounded corners, but it has not been implemented on the web yet.
 * The implementation of Web Target will revert to RoundedCornerShape.
 */
actual fun ContinuousRoundedCornerShapeImpl(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    smooth: Float
): CornerBasedShape = RoundedCornerShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart,
)