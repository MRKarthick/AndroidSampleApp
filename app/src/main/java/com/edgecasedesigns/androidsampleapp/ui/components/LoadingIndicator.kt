package com.edgecasedesigns.androidsampleapp.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edgecasedesigns.androidsampleapp.R

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xFF060932)),
        contentAlignment = Alignment.Center
    ) {
        LoadingIcon()
    }
}

@Composable
fun LoadingIcon() {
    val vectorIds = listOf(
        R.drawable.loader_1,
        R.drawable.loader_2,
        R.drawable.loader_3,
        R.drawable.loader_4
    )

    val infiniteTransition = rememberInfiniteTransition()

    val currentIndexFloat by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (vectorIds.size - 1).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val currentIndex = currentIndexFloat.toInt()

    Box(modifier = Modifier.size(48.dp)) {
        Image(
            painter = painterResource(id = vectorIds[currentIndex]),
            contentDescription = "Loading animation",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingIndicatorPreview() {
    LoadingIndicator()
}