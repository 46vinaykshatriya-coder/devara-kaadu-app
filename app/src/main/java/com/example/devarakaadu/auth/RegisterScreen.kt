package com.example.devarakaadu.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.devarakaadu.database.User

@Composable
fun RegisterScreen(

    onRegister: (User) -> Unit,

    onBack: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "Create Account",

            style =
                MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(

            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(

            onClick = {

                onRegister(
                    User(
                        email = email,
                        password = password
                    )
                )
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("Register")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = {
                onBack()
            }
        ) {

            Text("Back to Login")
        }
    }
}