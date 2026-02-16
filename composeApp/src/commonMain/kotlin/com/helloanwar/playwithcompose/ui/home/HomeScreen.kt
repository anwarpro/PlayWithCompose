package com.helloanwar.playwithcompose.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Theme Colors ---
val BrandCyan = Color(0xFF13C8EC)
val BgDark = Color(0xFF101F22)
val CardSurface = Color(0xFF16282C)
val EditorHeaderBg = Color(0xFF0D1416)
val TextSlate = Color(0xFF94A3B8)

@Composable
fun ComposePlaygroundFull() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        // 1. Background Radial Glows
        BackgroundGradients()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 2. Navigation
            item { Navbar() }

            // 3. Hero Section
            item {
                Column(
                    modifier = Modifier.padding(top = 80.dp, bottom = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Compose ")
                            withStyle(SpanStyle(color = BrandCyan)) { append("Playground") }
                        },
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = (-1.5).sp
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Write, run, and preview Jetpack Compose UI directly in your browser.\nNo setup required. Instant feedback.",
                        color = TextSlate,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp,
                        fontSize = 18.sp,
                        modifier = Modifier.widthIn(max = 650.dp)
                    )
                }
            }

            // 4. Main IDE Container
            item { IdeContainer() }

            // 5. Features Grid
            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 80.dp)
                        .widthIn(max = 1200.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FeatureCard(Icons.Outlined.Bolt, "Instant Compilation", "Powered by the latest Kotlin/Wasm compiler for lightning-fast feedback.")
                    FeatureCard(Icons.Outlined.Devices, "Cross-Platform Preview", "Preview how your Composables look on mobile, desktop, and web.")
                    FeatureCard(Icons.Outlined.Share, "Share Snippets", "Generate unique URLs for your playgrounds to share with the community.")
                }
            }

            // 6. Footer
            item { FooterSection() }
        }
    }
}

@Composable
fun BackgroundGradients() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(BrandCyan.copy(alpha = 0.15f), Color.Transparent),
                center = Offset(size.width * 0.85f, 150f),
                radius = 600.dp.toPx()
            ),
            radius = 600.dp.toPx(),
            center = Offset(size.width * 0.85f, 150f)
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF2563EB).copy(alpha = 0.1f), Color.Transparent),
                center = Offset(150f, size.height * 0.85f),
                radius = 700.dp.toPx()
            ),
            radius = 700.dp.toPx(),
            center = Offset(150f, size.height * 0.85f)
        )
    }
}

@Composable
fun Navbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 40.dp)
            .drawBehind {
                drawLine(Color.White.copy(0.05f), Offset(0f, size.height), Offset(size.width, size.height), 2f)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(32.dp).background(BrandCyan, RoundedCornerShape(4.dp)), contentAlignment = Alignment.Center) {
                Text("C", fontWeight = FontWeight.Bold, color = BgDark)
            }
            Spacer(Modifier.width(12.dp))
            Text("Compose Playground", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Text("Docs", color = TextSlate, fontSize = 14.sp)
            Text("Examples", color = TextSlate, fontSize = 14.sp)
            Text("API Reference", color = TextSlate, fontSize = 14.sp)
            Icon(Icons.Default.Code, null, tint = TextSlate, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun IdeContainer() {
    Box(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .widthIn(max = 1200.dp)
            .height(640.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(0.1f), RoundedCornerShape(16.dp))
            .background(CardSurface)
    ) {
        Row(Modifier.fillMaxSize()) {
            // EDITOR (Left)
            Column(Modifier.weight(0.65f)) {
                Row(Modifier.fillMaxWidth().height(44.dp).background(EditorHeaderBg).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(Color(0xFFFF5F56), Color(0xFFFFBD2E), Color(0xFF27C93F)).forEach { Box(Modifier.size(10.dp).background(it, CircleShape)) }
                    }
                    Text("Main.kt", Modifier.weight(1f), textAlign = TextAlign.Center, color = TextSlate, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                    Icon(Icons.Default.ContentCopy, null, tint = TextSlate, modifier = Modifier.size(16.dp))
                }
                Box(Modifier.fillMaxSize().background(BgDark).padding(24.dp)) {
                    Text(text = getHighlightedCode(), fontFamily = FontFamily.Monospace, fontSize = 14.sp, lineHeight = 24.sp)
                }
            }

            // PREVIEW (Right)
            Column(Modifier.weight(0.35f).border(width = 1.dp, Color.White.copy(0.1f))) {
                Row(Modifier.fillMaxWidth().height(44.dp).background(Color.White.copy(0.02f)).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("PREVIEW", color = TextSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(6.dp).background(Color.Green, CircleShape))
                        Spacer(Modifier.width(6.dp))
                        Text("LIVE", color = Color.Green, fontSize = 10.sp)
                    }
                }
                Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Canvas(Modifier.fillMaxSize()) {
                        val space = 24.dp.toPx()
                        for (x in 0..(size.width / space).toInt()) {
                            for (y in 0..(size.height / space).toInt()) {
                                drawCircle(BrandCyan.copy(0.06f), 1.5f, Offset(x * space, y * space))
                            }
                        }
                    }
                    Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = CardSurface), shape = RoundedCornerShape(6.dp), border = BorderStroke(1.dp, Color.White.copy(0.15f))) {
                        Text("Hello Compose! Clicks: 0", color = Color.White)
                    }
                }
                // Console Output
                Column(Modifier.fillMaxWidth().height(140.dp).background(EditorHeaderBg).padding(16.dp)) {
                    Text("CONSOLE OUTPUT", color = TextSlate, fontSize = 10.sp)
                    Spacer(Modifier.height(8.dp))
                    Text("> Compilation started...", color = Color.Gray, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                    Text("> Compilation successful (420ms)", color = Color.Green, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                    Text("> App mounted to #root", color = BrandCyan, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                }
            }
        }
        // Floating Action Button
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp).height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandCyan),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Icon(Icons.Default.PlayCircleFilled, null, tint = BgDark)
            Spacer(Modifier.width(10.dp))
            Text("Run Preview", color = BgDark, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun RowScope.FeatureCard(icon: ImageVector, title: String, desc: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .background(CardSurface.copy(0.4f), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(0.05f), RoundedCornerShape(16.dp))
            .padding(32.dp)
    ) {
        Box(Modifier.size(48.dp).background(BrandCyan.copy(0.1f), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = BrandCyan)
        }
        Spacer(Modifier.height(24.dp))
        Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        Text(desc, color = TextSlate, fontSize = 14.sp, lineHeight = 22.sp)
    }
}

@Composable
fun FooterSection() {
    Column(Modifier.padding(horizontal = 40.dp, vertical = 40.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(color = Color.White.copy(0.05f))
        Spacer(Modifier.height(32.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("© 2023 Compose Playground. Built for the Kotlin community.", color = Color.Gray, fontSize = 13.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Text("Privacy", color = Color.Gray, fontSize = 13.sp)
                Text("Terms", color = Color.Gray, fontSize = 13.sp)
                Text("Feedback", color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}

fun getHighlightedCode(): AnnotatedString = buildAnnotatedString {
    val keyword = SpanStyle(color = Color(0xFFFF79C6))
    val function = SpanStyle(color = BrandCyan)
    val string = SpanStyle(color = Color(0xFFF1FA8C))

    withStyle(keyword) { append("import ") }
    append("androidx.compose.runtime.* \n\n")
    withStyle(keyword) { append("@Composable\nfun ") }
    withStyle(function) { append("App") }
    append("() {\n  ")
    withStyle(keyword) { append("var ") }
    append("count ")
    withStyle(keyword) { append("by ") }
    append("remember { mutableStateOf(0) }\n\n  Button(onClick = { count++ }) {\n    Text(")
    withStyle(string) { append("\"Clicks: \$count\"") }
    append(")\n  }\n}")
}