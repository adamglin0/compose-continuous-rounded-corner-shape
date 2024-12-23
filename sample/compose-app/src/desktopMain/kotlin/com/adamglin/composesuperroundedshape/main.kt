package com.adamglin.composesuperroundedshape

import SampleApp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.reload.DevelopmentEntryPoint

fun main() {
    application {
        val windowState = rememberWindowState(width = 360.dp, height = 700.dp)
        Window(state = windowState, onCloseRequest = ::exitApplication) {
            DevelopmentEntryPoint {
                SampleApp()
            }
        }
    }
}