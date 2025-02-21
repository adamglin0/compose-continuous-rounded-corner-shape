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
 * This shape will automatically mirror the corner sizes in [LayoutDirection.Rtl], use
 * [AbsoluteContinuousRoundedCornerShape] for the layout direction unaware version of this shape.
 *
 * @param topStart a size of the top start corner
 * @param topEnd a size of the top end corner
 * @param bottomEnd a size of the bottom end corner
 * @param bottomStart a size of the bottom start corner
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 */
abstract class ContinuousRoundedCornerShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    val smooth: Float,
) : CornerBasedShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart,
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
        topStart: CornerSize = this.topStart,
        topEnd: CornerSize = this.topEnd,
        bottomEnd: CornerSize = this.bottomEnd,
        bottomStart: CornerSize = this.bottomStart,
        smooth: Float = this.smooth,
    ): ContinuousRoundedCornerShape

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): ContinuousRoundedCornerShape = copy(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        smooth = smooth,
    )
}

internal expect fun ContinuousRoundedCornerShapeImpl(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    smooth: Float,
): CornerBasedShape

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param corner [CornerSize] to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    corner: CornerSize,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShapeImpl(corner, corner, corner, corner, smooth)

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param size Size in [Dp] to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    size: Dp, @FloatRange(from = 0.1, to = 1.0)
    smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShape(CornerSize(size), smooth)

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param size Size in pixels to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    size: Float, @FloatRange(from = 0.1, to = 1.0)
    smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShape(CornerSize(size), smooth)

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 *
 * @param percent Size in percents to apply.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    percent: Int, @FloatRange(from = 0.1, to = 1.0)
    smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShape(CornerSize(percent), smooth)


/**
 * Creates [ContinuousRoundedCornerShape] with sizes defined in [Dp].
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    @FloatRange(from = 0.1, to = 1.0)
    smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShapeImpl(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        smooth = smooth,
    )


/**
 * Creates [RoundedCornerShape] with sizes defined in pixels.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    topStart: Float = 0.0f,
    topEnd: Float = 0.0f,
    bottomEnd: Float = 0.0f,
    bottomStart: Float = 0.0f,
    @FloatRange(from = 0.1, to = 1.0)
    smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShapeImpl(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        smooth = smooth
    )

/**
 * Creates [ContinuousRoundedCornerShape] with sizes defined in percents of the shape's smaller side.
 *
 * @param topStartPercent The top start corner radius as a percentage of the smaller side, with a
 *   range of 0 - 100.
 * @param topEndPercent The top end corner radius as a percentage of the smaller side, with a range
 *   of 0 - 100.
 * @param bottomEndPercent The bottom end corner radius as a percentage of the smaller side, with a
 *   range of 0 - 100.
 * @param bottomStartPercent The bottom start corner radius as a percentage of the smaller side,
 *   with a range of 0 - 100.
 * @param smooth a value to apply a smooth transition to the corners for a more seamless rounded effect.
 *               When set to 0, the effect is the same as [RoundedCornerShape], with no smoothness applied.
 *               The maximum value is 1, which provides the maximum smoothness for the rounded corners.
 *               The default value is 00.6f, which is commonly used in iOS-like designs to achieve a smooth yet subtle effect.
 */
fun ContinuousRoundedCornerShape(
    @IntRange(from = 0, to = 100) topStartPercent: Int = 0,
    @IntRange(from = 0, to = 100) topEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomStartPercent: Int = 0,
    @FloatRange(from = 0.1, to = 1.0) smooth: Float = 0.6f
) =
    ContinuousRoundedCornerShapeImpl(
        topStart = CornerSize(topStartPercent),
        topEnd = CornerSize(topEndPercent),
        bottomEnd = CornerSize(bottomEndPercent),
        bottomStart = CornerSize(bottomStartPercent),
        smooth = smooth
    )