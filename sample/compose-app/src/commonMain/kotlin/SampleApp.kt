import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.adamglin.composecontinuousroundedcornershape.ContinuousRoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SampleApp() {
    var direction by remember { mutableStateOf(LayoutDirection.Ltr) }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.widthIn(max = 400.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var topStartCorner by remember { mutableStateOf(0.dp) }
            var topEndCorner by remember { mutableStateOf(0.dp) }
            var bottomEndCorner by remember { mutableStateOf(0.dp) }
            var bottomStartCorner by remember { mutableStateOf(0.dp) }

            var smooth by remember { mutableStateOf(0.6f) }

            val maxShapeSize = 200.dp
            CompositionLocalProvider(
                LocalLayoutDirection provides direction
            ) {
                Box(modifier = Modifier.background(Color.White)) {
                    Box(
                        Modifier.size(maxShapeSize)
                            .background(
                                color = Color.Red,
                                shape = RoundedCornerShape(
                                    topStartCorner,
                                    topEndCorner,
                                    bottomEndCorner,
                                    bottomStartCorner,
                                )
                            )
                    )
                    Box(
                        Modifier.size(maxShapeSize)
                            .background(
                                color = Color.Black,
                                shape = ContinuousRoundedCornerShape(
                                    topStartCorner,
                                    topEndCorner,
                                    bottomEndCorner,
                                    bottomStartCorner,
                                    smooth,
                                )
                            )
                    )
                    DraggablePoint(
                        cornerType = Alignment.TopStart,
                        value = topStartCorner,
                        valueRange = 0.dp..maxShapeSize,
                        onValueChange = { topStartCorner = it },
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    DraggablePoint(
                        cornerType = Alignment.TopStart,
                        value = topStartCorner,
                        valueRange = 0.dp..maxShapeSize,
                        onValueChange = { topStartCorner = it },
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    DraggablePoint(
                        cornerType = Alignment.TopEnd,
                        value = topEndCorner,
                        valueRange = 0.dp..maxShapeSize,
                        onValueChange = { topEndCorner = it },
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                    DraggablePoint(
                        cornerType = Alignment.BottomEnd,
                        value = bottomEndCorner,
                        valueRange = 0.dp..maxShapeSize,
                        onValueChange = { bottomEndCorner = it },
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                    DraggablePoint(
                        cornerType = Alignment.BottomStart,
                        value = bottomStartCorner,
                        valueRange = 0.dp..maxShapeSize,
                        onValueChange = { bottomStartCorner = it },
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
            }


            Column(
                modifier = Modifier.padding(top = 40.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text("all corner")
                val averageValueDp = (topStartCorner + bottomEndCorner + bottomStartCorner + topEndCorner) / 4
                val averageValue = averageValueDp.value
                Slider(
                    value = averageValue,
                    onValueChange = {
                        topStartCorner = it.dp
                        bottomEndCorner = it.dp
                        bottomStartCorner = it.dp
                        topEndCorner = it.dp
                    },
                    valueRange = 0f..maxShapeSize.value
                )

                Text("smooth")
                Slider(
                    value = smooth,
                    onValueChange = { smooth = it },
                    valueRange = 0f..1f
                )
                Text("LocalLayoutDirection : $direction")
                Switch(
                    checked = direction == LayoutDirection.Ltr,
                    onCheckedChange = {
                        if (it) {
                            direction = LayoutDirection.Ltr
                        } else {
                            direction = LayoutDirection.Rtl
                        }
                    }
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggablePoint(
    cornerType: Alignment,
    value: Dp,
    valueRange: ClosedRange<Dp>,
    onValueChange: (Dp) -> Unit,
    modifier: Modifier = Modifier,
) {
    val draggable2DState = rememberDraggable2DState {
        val convertedValue = when (cornerType) {
            Alignment.TopStart -> it.x
            Alignment.TopEnd -> it.y
            Alignment.BottomEnd -> -it.x
            Alignment.BottomStart -> -it.y
            else -> 0f
        }
        onValueChange((value + convertedValue.dp).coerceIn(valueRange))
    }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(Color(0xff68689b))
            .draggable2D(draggable2DState) then modifier
    )
}