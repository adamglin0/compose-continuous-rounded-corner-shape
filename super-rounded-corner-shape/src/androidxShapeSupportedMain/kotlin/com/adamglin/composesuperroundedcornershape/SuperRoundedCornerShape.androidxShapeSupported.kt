package com.adamglin.composesuperroundedcornershape

import androidx.collection.FloatFloatPair
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
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
    return SuperRoundedShape(
        topStart = CornerSize(value),
        topEnd = CornerSize(value),
        bottomEnd = CornerSize(value),
        bottomStart = CornerSize(value),
        smooth = smooth
    )
}

class SuperRoundedShape(
    val topStart: CornerSize,
    val topEnd: CornerSize,
    val bottomEnd: CornerSize,
    val bottomStart: CornerSize,
    val smooth: Float = .6f,
) : Shape {
    private val path = Path()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val c0 = topStart.toPx(size, density)
        val c1 = topEnd.toPx(size, density)
        val c2 = bottomEnd.toPx(size, density)
        val c3 = bottomStart.toPx(size, density)
        val polygon = RoundedPolygon.Companion.rectangle(
            width = size.width,
            height = size.height,
            perVertexRounding = listOf(
                CornerRounding(
                    c0,
                    smoothing = smooth
                ),
                CornerRounding(
                    c1,
                    smoothing = smooth
                ),
                CornerRounding(
                    c2,
                    smoothing = smooth
                ),
                CornerRounding(
                    c3,
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
        val bounds = polygon.getBounds()
        val maxDimension = max(bounds.width, bounds.height)
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