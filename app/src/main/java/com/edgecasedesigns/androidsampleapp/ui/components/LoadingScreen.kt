package com.edgecasedesigns.androidsampleapp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.edgecasedesigns.androidsampleapp.R
import com.edgecasedesigns.androidsampleapp.ui.theme.CatchDesignRed

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top colored rectangle bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF060932))
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            LoadingIconLoop()
        }

        // Center column for logo and animated SVGs
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
                .background(CatchDesignRed),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(132.dp, 60.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

//*
@Composable
fun LoadingIconLoop() {
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
            animation = tween(durationMillis = 800, easing = LinearEasing),
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
//*/