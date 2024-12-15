import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adamglin.composesuperroundedcornershape.SuperRoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var shapeCornerSize by remember { mutableStateOf(20.dp) }
        var smoothSize by remember { mutableStateOf(0.6f) }
        Box(
            Modifier.size(100.dp).clip(SuperRoundedCornerShape(shapeCornerSize, smoothSize)).background(Color.Black)
        )

        Text("corner size")
        Slider(
            value = shapeCornerSize.value,
            onValueChange = { shapeCornerSize = it.dp },
            valueRange = 0f..100f
        )

        Text("smooth")
        Slider(
            value = smoothSize,
            onValueChange = { smoothSize = it },
            valueRange = 0f..1f
        )

    }
}