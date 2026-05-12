package com.example.devarakaadu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devarakaadu.data.Grove

@Composable
fun HomeScreen(

    groves: List<Grove>,

    onGroveClick: (Grove) -> Unit,

    onLogout: () -> Unit,

    onProfileClick: () -> Unit,

    onMapClick: () -> Unit
) {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF4F7F4)
            )
    ) {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding =
                PaddingValues(bottom = 30.dp)
        ) {

            item {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp)
                        .background(

                            Brush.verticalGradient(

                                colors = listOf(

                                    Color(0xFF0B3D2E),

                                    Color(0xFF145A32),

                                    Color(0xFF1E8449)
                                )
                            ),

                            shape = RoundedCornerShape(

                                bottomStart = 34.dp,

                                bottomEnd = 34.dp
                            )
                        )
                ) {

                    Column(

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(22.dp)
                    ) {

                        Row(

                            modifier =
                                Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween,

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Column {

                                Text(

                                    text = "DevaraKaadu",

                                    color = Color.White,

                                    fontSize = 32.sp,

                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(4.dp)
                                )

                                Text(

                                    text =
                                        "Protecting Sacred Groves",

                                    color =
                                        Color.White.copy(alpha = 0.82f),

                                    fontSize = 15.sp
                                )
                            }

                            Row {

                                Surface(

                                    modifier =
                                        Modifier.size(48.dp),

                                    shape =
                                        CircleShape,

                                    color =
                                        Color.White.copy(alpha = 0.16f)
                                ) {

                                    IconButton(

                                        onClick = {

                                            onMapClick()
                                        }
                                    ) {

                                        Icon(

                                            Icons.Default.LocationOn,

                                            null,

                                            tint = Color.White
                                        )
                                    }
                                }

                                Spacer(
                                    modifier =
                                        Modifier.width(10.dp)
                                )

                                Surface(

                                    modifier =
                                        Modifier.size(48.dp),

                                    shape =
                                        CircleShape,

                                    color =
                                        Color.White.copy(alpha = 0.16f)
                                ) {

                                    IconButton(

                                        onClick = {

                                            onProfileClick()
                                        }
                                    ) {

                                        Icon(

                                            Icons.Default.Person,

                                            null,

                                            tint = Color.White
                                        )
                                    }
                                }

                                Spacer(
                                    modifier =
                                        Modifier.width(10.dp)
                                )

                                Surface(

                                    modifier =
                                        Modifier.size(48.dp),

                                    shape =
                                        CircleShape,

                                    color =
                                        Color.White.copy(alpha = 0.16f)
                                ) {

                                    IconButton(

                                        onClick = {

                                            onLogout()
                                        }
                                    ) {

                                        Icon(

                                            Icons.Default.Logout,

                                            null,

                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(
                            modifier =
                                Modifier.height(28.dp)
                        )

                        Card(

                            shape =
                                RoundedCornerShape(28.dp),

                            colors =
                                CardDefaults.cardColors(

                                    containerColor =
                                        Color.White
                                )
                        ) {

                            Row(

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(22.dp),

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Surface(

                                    modifier =
                                        Modifier.size(62.dp),

                                    shape =
                                        CircleShape,

                                    color =
                                        Color(0xFFEAF7EE)
                                ) {

                                    Box(

                                        contentAlignment =
                                            Alignment.Center
                                    ) {

                                        Icon(

                                            Icons.Default.WbSunny,

                                            null,

                                            tint =
                                                Color(0xFF2E7D32),

                                            modifier =
                                                Modifier.size(32.dp)
                                        )
                                    }
                                }

                                Spacer(
                                    modifier =
                                        Modifier.width(18.dp)
                                )

                                Column {

                                    Text(

                                        text =
                                            "Sacred Forest Monitoring",

                                        color =
                                            Color(0xFF1B4332),

                                        fontWeight =
                                            FontWeight.Bold,

                                        fontSize = 19.sp
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(6.dp)
                                    )

                                    Text(

                                        text =
                                            "Explore biodiversity, scan species, report issues and monitor grove ecosystem.",

                                        color =
                                            Color(0xFF5C6B63),

                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(22.dp)
                )

                Text(

                    text = "Sacred Groves",

                    modifier =
                        Modifier.padding(horizontal = 18.dp),

                    color = Color(0xFF1B4332),

                    fontSize = 26.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )
            }

            items(groves) { grove ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(

                            horizontal = 18.dp,

                            vertical = 10.dp
                        )
                        .clickable {

                            onGroveClick(grove)
                        },

                    shape =
                        RoundedCornerShape(30.dp),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                Color.White
                        )
                ) {

                    Column {

                        Box {

                            Image(

                                painter =
                                    painterResource(id = grove.image),

                                contentDescription = null,

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(230.dp),

                                contentScale =
                                    ContentScale.Crop
                            )

                            Box(

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(230.dp)
                                        .background(

                                            Brush.verticalGradient(

                                                colors = listOf(

                                                    Color.Transparent,

                                                    Color.Black.copy(alpha = 0.55f)
                                                )
                                            )
                                        )
                            )

                            Text(

                                text = grove.name,

                                color = Color.White,

                                fontSize = 25.sp,

                                fontWeight = FontWeight.Bold,

                                modifier =
                                    Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(20.dp)
                            )
                        }

                        Column(

                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Text(

                                text = grove.description,

                                color = Color(0xFF5C6B63),

                                fontSize = 15.sp,

                                maxLines = 3,

                                overflow =
                                    TextOverflow.Ellipsis
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(18.dp)
                            )

                            Button(

                                onClick = {

                                    onGroveClick(grove)
                                },

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(54.dp),

                                shape =
                                    RoundedCornerShape(18.dp),

                                colors =
                                    ButtonDefaults.buttonColors(

                                        containerColor =
                                            Color(0xFF2E7D32)
                                    )
                            ) {

                                Text(

                                    text = "Explore Grove",

                                    fontSize = 16.sp,

                                    fontWeight =
                                        FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}