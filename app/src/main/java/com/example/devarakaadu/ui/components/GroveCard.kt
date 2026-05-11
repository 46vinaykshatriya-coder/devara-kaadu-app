package com.example.devarakaadu.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devarakaadu.data.Grove

@Composable
fun GroveCard(
    grove: Grove,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable { onClick() }
            .animateContentSize(),

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFCF7)
        )
    ) {

        Column {

            Image(
                painter = painterResource(id = grove.image),
                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = grove.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = grove.description,
                    color = Color(0xFF6B6B6B),
                    fontSize = 14.sp
                )
            }
        }
    }
}