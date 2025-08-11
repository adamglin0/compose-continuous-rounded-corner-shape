@file:Suppress("FunctionName", "UNUSED")

package com.adamglin.composecontinuousroundedcornershape

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


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
abstract class BaseAbsoluteContinuousRoundedCornerShape(
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize,
    val smooth: Float,
) : CornerBasedShape(
    topStart = topLeft,
    topEnd = topRight,
    bottomEnd = bottomRight,
    bottomStart = bottomLeft
) {
    /**
     * Creates a copy of this Shape with a new corner sizes or smooth size.
     *
     * @param topStart a size of the top start corner
     * @param topEnd a size of the top end corner
     * @param bottomEnd a size of the bottom end corner
     * @param bottomStart a size of the bottom start corner
     */
    abstract fun copy(
        topLeft: CornerSize = this.topStart,
        topRight: CornerSize = this.topEnd,
        bottomRight: CornerSize = this.bottomEnd,
        bottomLeft: CornerSize = this.bottomStart,
        smooth: Float = this.smooth,
    ): BaseAbsoluteContinuousRoundedCornerShape

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): BaseAbsoluteContinuousRoundedCornerShape = copy(
        topLeft = topStart,
        topRight = topEnd,
        bottomRight = bottomEnd,
        bottomLeft = bottomStart,
        smooth = smooth,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbsoluteContinuousRoundedCornerShapeImpl) return false

        if (topStart != other.topStart) return false
        if (topEnd != other.topEnd) return false
        if (bottomEnd != other.bottomEnd) return false
        if (bottomStart != other.bottomStart) return false
        if (smooth != other.smooth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        result = 31 * result + smooth.hashCode()
        return result
    }
}


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
fun AbsoluteContinuousRoundedCornerShape(
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
    smooth = smooth,
)

/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param corner [CornerSize] to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    corner: CornerSize,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShapeImpl(corner, corner, corner, corner, smooth)

/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param size Size in [Dp] to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    size: Dp,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShape(CornerSize(size), smooth)

/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param size Size in pixels to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    size: Float,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShape(CornerSize(size), smooth)

/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param percent Size in percents to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    percent: Int,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShape(CornerSize(percent), smooth)


/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with sizes defined in [Dp].
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    topLeft: Dp = 0.dp,
    topRight: Dp = 0.dp,
    bottomRight: Dp = 0.dp,
    bottomLeft: Dp = 0.dp,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShapeImpl(
        topLeft = CornerSize(topLeft),
        topRight = CornerSize(topRight),
        bottomRight = CornerSize(bottomRight),
        bottomLeft = CornerSize(bottomLeft),
        smooth = smooth,
    )


/**
 * Creates [RoundedCornerShape] with sizes defined in pixels.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    topLeft: Float = 0.0f,
    topRight: Float = 0.0f,
    bottomRight: Float = 0.0f,
    bottomLeft: Float = 0.0f,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShapeImpl(
        topLeft = CornerSize(topLeft),
        topRight = CornerSize(topRight),
        bottomRight = CornerSize(bottomRight),
        bottomLeft = CornerSize(bottomLeft),
        smooth = smooth
    )

/**
 * Creates [AbsoluteContinuousRoundedCornerShape] with sizes defined in percents of the shape's smaller side.
 *
 * @param topLeftPercent The top left corner radius as a percentage of the smaller side, with a
 *   range of 0 - 100.
 * @param topRightPercent The top right corner radius as a percentage of the smaller side, with a
 *   range of 0 - 100.
 * @param bottomRightPercent The bottom right corner radius as a percentage of the smaller side,
 *   with a range of 0 - 100.
 * @param bottomLeftPercent The bottom left corner radius as a percentage of the smaller side, with
 *   a range of 0 - 100.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [AbsoluteRoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun AbsoluteContinuousRoundedCornerShape(
    @IntRange(from = 0, to = 100) topLeftPercent: Int = 0,
    @IntRange(from = 0, to = 100) topRightPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomRightPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomLeftPercent: Int = 0,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    AbsoluteContinuousRoundedCornerShapeImpl(
        topLeft = CornerSize(topLeftPercent),
        topRight = CornerSize(topRightPercent),
        bottomRight = CornerSize(bottomRightPercent),
        bottomLeft = CornerSize(bottomLeftPercent),
        smooth = smooth
    )