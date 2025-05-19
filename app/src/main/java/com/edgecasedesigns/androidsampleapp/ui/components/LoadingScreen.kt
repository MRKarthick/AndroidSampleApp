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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CatchDesignRed)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF060932)),
            contentAlignment = Alignment.Center
        ) {
            LoadingIconLoop()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // This Box will take the remaining vertical space
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(132.dp, 60.dp)
            )
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
//*/


/*

 */