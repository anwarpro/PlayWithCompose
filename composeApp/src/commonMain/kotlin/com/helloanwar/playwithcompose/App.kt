package com.helloanwar.playwithcompose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.helloanwar.playwithcompose.ui.home.ComposePlaygroundFull

@Composable
@Preview
fun App() {
    MaterialTheme {
        ComposePlaygroundFull()
    }
}