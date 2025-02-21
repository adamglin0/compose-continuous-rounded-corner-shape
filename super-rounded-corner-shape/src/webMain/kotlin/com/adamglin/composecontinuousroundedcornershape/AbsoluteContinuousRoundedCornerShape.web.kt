@file:Suppress("FunctionName")

package com.adamglin.composecontinuousroundedcornershape

import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize

/**
 * The expected shape is a rectangle with smooth rounded corners, but it has not been implemented on the web yet.
 * The implementation of Web Target will revert to RoundedCornerShape.
 */
actual fun AbsoluteContinuousRoundedCornerShapeImpl(
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize,
    smooth: Float
): CornerBasedShape = AbsoluteRoundedCornerShape(
    topLeft = topLeft,
    topRight = topRight,
    bottomRight = bottomRight,
    bottomLeft = bottomLeft,
)