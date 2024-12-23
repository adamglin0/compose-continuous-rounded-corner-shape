package com.adamglin.composesuperroundedcornershape

import androidx.collection.FloatFloatPair
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle
import kotlin.jvm.JvmOverloads
import kotlin.math.max

actual fun SuperRoundedCornerShape(value: Dp, smooth: Float): Shape {
    return SuperRoundedCornerShape(
        topStart = CornerSize(value),
        topEnd = CornerSize(value),
        bottomEnd = CornerSize(value),
        bottomStart = CornerSize(value),
        smooth = smooth
    )
}

actual fun SuperRoundedCornerShape(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    smooth: Float
): Shape {
    return SuperRoundedCornerShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        smooth = smooth
    )
}

class SuperRoundedCornerShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    val smooth: Float = .6f,
) : CornerBasedShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
) {
    private val path = Path()
    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): CornerBasedShape {
        TODO("Not yet implemented")
    }

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

fun RoundedPolygon.getBounds() = calculateBounds().let { Rect(it[0], it[1], it[2], it[3]) }