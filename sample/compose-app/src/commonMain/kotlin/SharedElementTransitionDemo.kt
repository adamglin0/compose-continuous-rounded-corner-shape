import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adamglin.composecontinuousroundedcornershape.ContinuousRoundedCornerShape


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementTransitionDemo() {

    var showDetails by remember {
        mutableStateOf(false)
    }

    Column {
        SharedTransitionLayout {
            AnimatedContent(
                showDetails,
                label = "basic_transition"
            ) { targetState ->
                if (!targetState) {
                    MainContent(
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } else {
                    DetailsContent(
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
        Button(
            onClick = { showDetails = !showDetails }
        ) { Text("toggle") }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "box"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .clip(ContinuousRoundedCornerShape(4.dp))
                    .size(30.dp).background(Color.Red)
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "box"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .clip(ContinuousRoundedCornerShape(4.dp))
                    .size(30.dp).background(Color.Red)
            )
        }
    }
}