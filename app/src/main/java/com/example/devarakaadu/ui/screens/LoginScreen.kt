package com.example.devarakaadu.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devarakaadu.R
import com.example.devarakaadu.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(

    database: AppDatabase,

    onLoginSuccess: (String) -> Unit,

    onRegisterClick: () -> Unit
) {

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    var errorMessage by remember {

        mutableStateOf("")
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(

            painter =
                painterResource(
                    id = R.drawable.login_bg
                ),

            contentDescription = null,

            modifier =
                Modifier.fillMaxSize(),

            contentScale =
                ContentScale.Crop
        )

        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.45f),
                            Color.Black.copy(alpha = 0.65f)
                        )
                    )
                )
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(

                text = "Devara Kaadu",

                color = Color.White,

                fontSize = 42.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Text(

                text =
                    "AI-Powered Sacred Grove Conservation",

                color =
                    Color.White.copy(alpha = 0.85f),

                fontSize = 16.sp
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(32.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White.copy(alpha = 0.12f)
                    )
            ) {

                Column(

                    modifier = Modifier
                        .padding(26.dp)
                ) {

                    OutlinedTextField(

                        value = email,

                        onValueChange = {

                            email = it
                        },

                        label = {

                            Text(
                                "Email",
                                color = Color.White
                            )
                        },

                        singleLine = true,

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Email
                            ),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    Color.White,

                                unfocusedBorderColor =
                                    Color.White.copy(alpha = 0.6f),

                                focusedTextColor =
                                    Color.White,

                                unfocusedTextColor =
                                    Color.White,

                                cursorColor =
                                    Color.White,

                                focusedLabelColor =
                                    Color.White,

                                unfocusedLabelColor =
                                    Color.White.copy(alpha = 0.8f)
                            ),

                        modifier =
                            Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    OutlinedTextField(

                        value = password,

                        onValueChange = {

                            password = it
                        },

                        label = {

                            Text(
                                "Password",
                                color = Color.White
                            )
                        },

                        singleLine = true,

                        visualTransformation =
                            PasswordVisualTransformation(),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    Color.White,

                                unfocusedBorderColor =
                                    Color.White.copy(alpha = 0.6f),

                                focusedTextColor =
                                    Color.White,

                                unfocusedTextColor =
                                    Color.White,

                                cursorColor =
                                    Color.White,

                                focusedLabelColor =
                                    Color.White,

                                unfocusedLabelColor =
                                    Color.White.copy(alpha = 0.8f)
                            ),

                        modifier =
                            Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier =
                            Modifier.height(28.dp)
                    )

                    Button(

                        onClick = {

                            CoroutineScope(
                                Dispatchers.IO
                            ).launch {

                                val user =
                                    database.userDao()
                                        .loginUser(
                                            email,
                                            password
                                        )

                                withContext(
                                    Dispatchers.Main
                                ) {

                                    if (user != null) {

                                        onLoginSuccess(email)

                                    } else {

                                        errorMessage =
                                            "Invalid email or password"
                                    }
                                }
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF2D6A4F)
                            )
                    ) {

                        Text(

                            text = "Login",

                            fontSize = 18.sp,

                            color = Color.White
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    TextButton(

                        onClick = {

                            onRegisterClick()
                        }
                    ) {

                        Text(

                            text =
                                "Create Account",

                            color = Color.White,

                            fontSize = 16.sp
                        )
                    }

                    if (errorMessage.isNotEmpty()) {

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Text(

                            text = errorMessage,

                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}