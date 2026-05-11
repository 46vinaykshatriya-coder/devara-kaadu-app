package com.example.devarakaadu.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(onLogout: () -> Unit) {

    TopAppBar(

        title = {
            Text(
                text = "Devara-Kaadu",
                color = Color(0xFFF5F1E8)
            )
        },

        actions = {

            IconButton(onClick = { onLogout() }) {

                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color(0xFFF5F1E8)
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1F2A24)
        )
    )
}