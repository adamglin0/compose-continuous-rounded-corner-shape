package com.adamglin.composecontinuousroundedcornershape

import androidx.collection.FloatFloatPair
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle
import kotlin.jvm.JvmOverloads

class ContinuousRoundedCornerShapeImpl(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    smooth: Float,
) : BaseContinuousRoundedCornerShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart,
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
                    topLeft = CornerRadius(if (layoutDirection == LayoutDirection.Ltr) topStart else topEnd),
                    topRight = CornerRadius(if (layoutDirection == LayoutDirection.Ltr) topEnd else topStart),
                    bottomRight =
                        CornerRadius(if (layoutDirection == LayoutDirection.Ltr) bottomEnd else bottomStart),
                    bottomLeft =
                        CornerRadius(if (layoutDirection == LayoutDirection.Ltr) bottomStart else bottomEnd)
                )
            )

            else -> {
                val (adjustedTopStart, adjustedTopEnd, adjustedBottomEnd, adjustedBottomStart) =
                    if (layoutDirection == LayoutDirection.Ltr) {
                        listOf(topStart, topEnd, bottomEnd, bottomStart)
                    } else {
                        listOf(topEnd, topStart, bottomStart, bottomEnd)
                    }
                val polygon = RoundedPolygon.rectangle(
                    width = size.width,
                    height = size.height,
                    perVertexRounding = listOf(
                        CornerRounding(adjustedBottomEnd, smooth),
                        CornerRounding(adjustedBottomStart, smooth),
                        CornerRounding(adjustedTopStart, smooth),
                        CornerRounding(adjustedTopEnd, smooth)
                    )
                ).transformed { ox, oy ->
                    FloatFloatPair(ox + size.center.x, oy + size.center.y)
                }
                path.rewind()
                polygon.toPath(path)
                return Outline.Generic(path)
            }
        }
    }

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize,
        smooth: Float,
    ) = ContinuousRoundedCornerShapeImpl(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        smooth = smooth
    )

    override fun toString(): String {
        return "ContinuousRoundedCornerShapeAndroidxShapeImpl(topStart = $topStart, topEnd = $topEnd, bottomEnd = " +
                "$bottomEnd, bottomStart = $bottomStart, smooth = $smooth)"
    }
}

/**
 * Gets a [Path] representation for a [RoundedPolygon] shape, which can be used to draw the
 * polygon.
 *
 * @param path an optional [Path] object which, if supplied, will avoid the function having
 * to create a new [Path] object
 */
@JvmOverloads
fun RoundedPolygon.toPath(path: Path = Path()): Path {
    pathFromCubicList(path, cubics)
    return path
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
