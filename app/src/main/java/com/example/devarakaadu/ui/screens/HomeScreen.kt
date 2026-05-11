package com.example.devarakaadu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.devarakaadu.data.Grove

@Composable
fun HomeScreen(

    groves: List<Grove>,

    onGroveClick: (Grove) -> Unit,

    onLogout: () -> Unit,

    onProfileClick: () -> Unit
) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFFF7F8F4),

                        Color(0xFFF1F5EF)
                    )
                )
            )
    ) {

        Row(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 22.dp
                    ),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column {

                Text(

                    text = "Devara Kaadu",

                    fontSize = 34.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF183A2D)
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(

                    text =
                        "Sacred Grove Explorer",

                    fontSize = 15.sp,

                    color = Color(0xFF66756C)
                )
            }

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(

                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFE7ECE7)
                            )
                            .clickable {

                                onProfileClick()
                            },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.AccountCircle,

                        contentDescription = null,

                        tint =
                            Color(0xFF1B4332),

                        modifier =
                            Modifier.size(30.dp)
                    )
                }

                Spacer(
                    modifier =
                        Modifier.width(12.dp)
                )

                Box(

                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFD62828)
                            )
                            .clickable {

                                onLogout()
                            },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Logout,

                        contentDescription = null,

                        tint = Color.White,

                        modifier =
                            Modifier.size(24.dp)
                    )
                }
            }
        }

        LazyColumn(

            modifier =
                Modifier.fillMaxSize(),

            contentPadding =
                PaddingValues(
                    start = 18.dp,
                    end = 18.dp,
                    bottom = 24.dp
                ),

            verticalArrangement =
                Arrangement.spacedBy(20.dp)
        ) {

            items(

                items = groves,

                key = {

                    it.name
                }
            ) { grove ->

                PremiumGroveCard(

                    grove = grove,

                    onClick = {

                        onGroveClick(grove)
                    }
                )
            }
        }
    }
}

@Composable
fun PremiumGroveCard(

    grove: Grove,

    onClick: () -> Unit
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {

                    onClick()
                },

        shape =
            RoundedCornerShape(24.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
    ) {

        Column {

            Box {

                AsyncImage(

                    model = grove.image,

                    contentDescription = null,

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(210.dp),

                    contentScale =
                        ContentScale.Crop
                )

                Box(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .background(

                                Brush.verticalGradient(

                                    colors = listOf(

                                        Color.Transparent,

                                        Color.Black.copy(alpha = 0.30f)
                                    )
                                )
                            )
                )

                Text(

                    text = grove.name,

                    color = Color.White,

                    fontSize = 28.sp,

                    fontWeight = FontWeight.Bold,

                    modifier =
                        Modifier
                            .align(
                                Alignment.BottomStart
                            )
                            .padding(20.dp)
                )
            }

            Column(

                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(

                    text = grove.description,

                    fontSize = 15.sp,

                    lineHeight = 23.sp,

                    color = Color(0xFF5A6A61)
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                Button(

                    onClick = {

                        onClick()
                    },

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(52.dp),

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor =
                                Color(0xFF2D6A4F)
                        ),

                    shape =
                        RoundedCornerShape(16.dp)
                ) {

                    Text(

                        text = "Explore Grove",

                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Icon(

                        Icons.Default.ArrowOutward,

                        contentDescription = null,

                        modifier =
                            Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}