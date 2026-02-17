package com.helloanwar.playwithcompose.ui.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
actual fun CodeEditor(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier
) {
    val visualTransformation = remember {
        VisualTransformation { text ->
            TransformedText(
                text = highlightKotlinCode(text.text), // Apply highlighting
                offsetMapping = OffsetMapping.Identity
            )
        }
    }
    
    val lineCount = remember(content) { content.split('\n').size }
    val scrollState = rememberScrollState()
    
    // Style constants
    val fontSize = 14.sp
    val lineHeight = 20.sp
    val fontFamily = FontFamily.Monospace

    Box(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFF272822))
        ) {
            // Line Numbers
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(end = 12.dp, start = 8.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.End
            ) {
                repeat(lineCount) { index ->
                    androidx.compose.material3.Text(
                        text = "${index + 1}",
                        color = Color(0xFF75715E),
                        fontSize = fontSize,
                        fontFamily = fontFamily,
                        lineHeight = lineHeight,
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )
                }
            }

            // Divider line
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight() // Within the scrollable row this might be problematic if not constrained? No, Row height is determined by max child.
                    // Actually, if we use fillMaxHeight inside a scrollable column (vertical), it fills the viewport or the content? 
                    // Inside a Row, it fills the Row's height. Since Column grows with content, this should grow with it.
                    .background(Color(0xFF3E3D32))
            )

            // Editor
            BasicTextField(
                value = content,
                onValueChange = onContentChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                textStyle = TextStyle(
                    color = Color(0xFFF8F8F2),
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    lineHeight = lineHeight
                ),
                cursorBrush = SolidColor(Color.White),
                visualTransformation = visualTransformation
            )
        }
    }
}

private fun highlightKotlinCode(code: String): AnnotatedString {
    return buildAnnotatedString {
        append(code)
        
        // Colors (Monokai-ish)
        val keywordColor = Color(0xFFF92672) // Pink
        val stringColor = Color(0xFFE6DB74)  // Yellow
        val commentColor = Color(0xFF75715E) // Grey
        val funNameColor = Color(0xFFA6E22E) // Green
        val numberColor = Color(0xFFAE81FF)  // Purple

        // Keywords
        val keywords = listOf(
            "package", "import", "class", "interface", "object", "val", "var", "fun",
            "return", "if", "else", "for", "while", "do", "when", "true", "false", "null",
            "this", "super", "try", "catch", "finally", "throw", "is", "in", "as",
            "break", "continue", "typealias", "constructor", "init", "companion", "override",
            "private", "protected", "public", "internal", "@Composable"
        )
        
        val keywordRegex = Regex("\\b(${keywords.joinToString("|")})\\b")
        keywordRegex.findAll(code).forEach { matchResult ->
            addStyle(SpanStyle(color = keywordColor), matchResult.range.first, matchResult.range.last + 1)
        }

        // Strings (simple double quotes)
        val stringRegex = Regex("\"(.*?)\"")
        stringRegex.findAll(code).forEach { matchResult ->
            addStyle(SpanStyle(color = stringColor), matchResult.range.first, matchResult.range.last + 1)
        }
        
        // Numbers
        val numberRegex = Regex("\\b\\d+\\b")
        numberRegex.findAll(code).forEach { matchResult ->
            addStyle(SpanStyle(color = numberColor), matchResult.range.first, matchResult.range.last + 1)
        }

        // Comments (single line)
        val commentRegex = Regex("//.*")
        commentRegex.findAll(code).forEach { matchResult ->
            addStyle(SpanStyle(color = commentColor), matchResult.range.first, matchResult.range.last + 1)
        }
    }
}