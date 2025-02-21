@file:Suppress("FunctionName", "UNUSED")

package com.adamglin.composecontinuousroundedcornershape

import androidx.collection.FloatFloatPair
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle

actual fun AbsoluteContinuousRoundedCornerShapeImpl(
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize,
    smooth: Float
): CornerBasedShape = AbsoluteContinuousRoundedCornerShapeAndroidxShapeImpl(
    topLeft = topLeft,
    topRight = topRight,
    bottomRight = bottomRight,
    bottomLeft = bottomLeft,
    smooth = smooth
)


private class AbsoluteContinuousRoundedCornerShapeAndroidxShapeImpl(
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize,
    smooth: Float,
) : AbsoluteContinuousRoundedCornerShape(
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
        if (size.minDimension == 0f) {
            return Outline.Rectangle(size.toRect())
        }
        val polygon = RoundedPolygon.Companion.rectangle(
            width = size.width,
            height = size.height,
            perVertexRounding = listOf(
                CornerRounding(
                    bottomEnd,
                    smoothing = smooth
                ),
                CornerRounding(
                    bottomStart,
                    smoothing = smooth
                ),
                CornerRounding(
                    topStart,
                    smoothing = smooth
                ),
                CornerRounding(
                    topEnd,
                    smoothing = smooth
                )
            )
        ).transformed { ox, oy ->
            FloatFloatPair(
                ox + size.center.x, oy + size.center.y
            )
        }
        path.rewind()
        polygon.toPath(path)
//        val bounds = polygon.getBounds()
//        val maxDimension = max(bounds.width, bounds.height)
        return Outline.Generic(path)
    }

    override fun copy(
        topLeft: CornerSize,
        topRight: CornerSize,
        bottomRight: CornerSize,
        bottomLeft: CornerSize,
        smooth: Float,
    ) = AbsoluteContinuousRoundedCornerShapeAndroidxShapeImpl(
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbsoluteContinuousRoundedCornerShapeAndroidxShapeImpl) return false

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

private fun pathFromCubicList(
    path: Path,
    cubicList: List<Cubic>
) {
    var first = true
    path.rewind()
    for (element in cubicList) {
        if (first) {
            path.moveTo(element.anchor0X, element.anchor0Y)
            first = false
        }
        path.cubicTo(
            element.control0X, element.control0Y, element.control1X, element.control1Y,
            element.anchor1X, element.anchor1Y
        )
    }
    path.close()
}