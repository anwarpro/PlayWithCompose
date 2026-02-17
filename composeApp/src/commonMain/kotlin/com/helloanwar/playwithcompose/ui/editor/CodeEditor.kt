package com.helloanwar.playwithcompose.ui.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CodeEditor(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
)
