package com.example.devarakaadu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devarakaadu.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(

    onNavigate: () -> Unit
) {

    LaunchedEffect(Unit) {

        delay(2500)

        onNavigate()
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize(),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Image(

                painter =
                    painterResource(
                        id = R.drawable.app_logo
                    ),

                contentDescription = null,

                modifier =
                    Modifier.size(150.dp)
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Text(

                text = "Devara Kaadu",

                fontSize = 38.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF1B4332)
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(

                text =
                    "AI-Powered Sacred Grove Conservation",

                color =
                    Color.DarkGray,

                fontSize = 16.sp
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            CircularProgressIndicator(

                color = Color(0xFF1B4332)
            )
        }
    }
}