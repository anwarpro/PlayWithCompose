package com.helloanwar.playwithcompose.ui.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
actual fun CodeEditor(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = content,
        onValueChange = onContentChange,
        modifier = modifier.fillMaxSize(),
        textStyle = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Monospace),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF272822),
            unfocusedContainerColor = Color(0xFF272822),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}
