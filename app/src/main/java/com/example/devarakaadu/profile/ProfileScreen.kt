package com.example.devarakaadu.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(

    userEmail: String,

    onBack: () -> Unit
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
            .padding(20.dp)
    ) {

        Row(

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(

                onClick = {

                    onBack()
                }

            ) {

                Icon(

                    Icons.Default.ArrowBack,

                    contentDescription = null,

                    tint = Color(0xFF183A2D)
                )
            }

            Spacer(
                modifier =
                    Modifier.width(6.dp)
            )

            Text(

                text = "My Profile",

                fontSize = 28.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF183A2D)
            )
        }

        Spacer(
            modifier =
                Modifier.height(30.dp)
        )

        Card(

            shape =
                RoundedCornerShape(28.dp),

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        Color.White
                ),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Box(

                    modifier =
                        Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFE4ECE5)
                            ),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        Icons.Default.Person,

                        contentDescription = null,

                        tint = Color(0xFF1B4332),

                        modifier =
                            Modifier.size(60.dp)
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                Text(

                    text = "Forest Explorer",

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF183A2D)
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(

                    text = userEmail,

                    fontSize = 15.sp,

                    color = Color(0xFF66756C)
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(28.dp)
        )

        ProfileOption(

            icon = {

                Icon(
                    Icons.Default.Email,
                    null,
                    tint = Color(0xFF2D6A4F)
                )
            },

            title = "Email Address",

            subtitle = userEmail
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        ProfileOption(

            icon = {

                Icon(
                    Icons.Default.Forest,
                    null,
                    tint = Color(0xFF2D6A4F)
                )
            },

            title = "Conservation Status",

            subtitle = "Active Sacred Grove Volunteer"
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        ProfileOption(

            icon = {

                Icon(
                    Icons.Default.Badge,
                    null,
                    tint = Color(0xFF2D6A4F)
                )
            },

            title = "Reports Submitted",

            subtitle = "12 Environmental Reports"
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        ProfileOption(

            icon = {

                Icon(
                    Icons.Default.Settings,
                    null,
                    tint = Color(0xFF2D6A4F)
                )
            },

            title = "Application Version",

            subtitle = "Devara Kaadu v1.0"
        )
    }
}

@Composable
fun ProfileOption(

    icon: @Composable () -> Unit,

    title: String,

    subtitle: String
) {

    Card(

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),

        modifier =
            Modifier.fillMaxWidth()
    ) {

        Row(

            modifier =
                Modifier.padding(18.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(

                modifier =
                    Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            Color(0xFFEAF4EA)
                        ),

                contentAlignment =
                    Alignment.Center
            ) {

                icon()
            }

            Spacer(
                modifier =
                    Modifier.width(16.dp)
            )

            Column {

                Text(

                    text = title,

                    fontSize = 16.sp,

                    fontWeight = FontWeight.SemiBold,

                    color = Color(0xFF183A2D)
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(

                    text = subtitle,

                    fontSize = 14.sp,

                    color = Color(0xFF66756C)
                )
            }
        }
    }
}