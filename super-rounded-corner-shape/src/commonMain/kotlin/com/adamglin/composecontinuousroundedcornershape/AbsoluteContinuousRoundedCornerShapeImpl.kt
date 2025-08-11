package com.adamglin.composecontinuousroundedcornershape

import androidx.collection.FloatFloatPair
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle

/**
 * A shape describing the rectangle with continuous rounded corners similar to the Apple system.
 *
 * This shape will not automatically mirror the corner sizes in [LayoutDirection.Rtl], use
 * [ContinuousRoundedCornerShape] for the layout direction aware version of this shape.
 *
 * @param topLeft a size of the top left corner
 * @param topRight a size of the top right corner
 * @param bottomRight a size of the bottom right corner
 * @param bottomLeft a size of the bottom left corner
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
class AbsoluteContinuousRoundedCornerShapeImpl(
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize,
    smooth: Float,
) : BaseAbsoluteContinuousRoundedCornerShape(
    topLeft = topLeft,
    topRight = topRight,
    bottomRight = bottomRight,
    bottomLeft = bottomLeft,
    smooth = smooth
) {
    private val path = Path()

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        return when {
            size.minDimension == 0f -> Outline.Rectangle(size.toRect())
            smooth == 0f -> Outline.Rounded(
                RoundRect(
                    rect = size.toRect(),
                    topLeft = CornerRadius(if (layoutDirection == Ltr) topStart else topEnd),
                    topRight = CornerRadius(if (layoutDirection == Ltr) topEnd else topStart),
                    bottomRight =
                        CornerRadius(if (layoutDirection == Ltr) bottomEnd else bottomStart),
                    bottomLeft =
                        CornerRadius(if (layoutDirection == Ltr) bottomStart else bottomEnd)
                )
            )

            else -> {
                val polygon = RoundedPolygon.rectangle(
                    width = size.width,
                    height = size.height,
                    perVertexRounding = listOf(
                        CornerRounding(bottomEnd, smoothing = smooth),
                        CornerRounding(bottomStart, smoothing = smooth),
                        CornerRounding(topStart, smoothing = smooth),
                        CornerRounding(topEnd, smoothing = smooth)
                    )
                ).transformed { ox, oy ->
                    FloatFloatPair(
                        ox + size.center.x, oy + size.center.y
                    )
                }
                path.rewind()
                polygon.toPath(path)
                return Outline.Generic(path)
            }
        }
    }

    override fun copy(
        topLeft: CornerSize,
        topRight: CornerSize,
        bottomRight: CornerSize,
        bottomLeft: CornerSize,
        smooth: Float,
    ) = AbsoluteContinuousRoundedCornerShapeImpl(
        topLeft = topLeft,
        topRight = topRight,
        bottomRight = bottomRight,
        bottomLeft = bottomLeft,
        smooth = smooth
    )

    override fun toString(): String {
        return "AbsoluteContinuousRoundedCornerShapeAndroidxShapeImpl(topLeft = $topStart, topRight = $topEnd, bottomRight = " +
                "$bottomEnd, bottomLeft = $bottomStart, smooth = $smooth)"
    }
}
