package com.example.devarakaadu.ui.screens

import android.graphics.Bitmap
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devarakaadu.data.Grove
import com.example.devarakaadu.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@Composable
fun DetailScreen(

    grove: Grove,

    onBack: () -> Unit
) {

    val context = LocalContext.current

    var capturedBitmap by remember {

        mutableStateOf<Bitmap?>(null)
    }

    var aiResult by remember {

        mutableStateOf("")
    }

    var loading by remember {

        mutableStateOf(false)
    }

    var showReportDialog by remember {

        mutableStateOf(false)
    }

    var selectedIssue by remember {

        mutableStateOf("")
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.TakePicturePreview()

        ) { bitmap ->

            bitmap?.let {

                capturedBitmap = it

                loading = true

                val outputStream =
                    ByteArrayOutputStream()

                it.compress(
                    Bitmap.CompressFormat.JPEG,
                    90,
                    outputStream
                )

                val base64Image =
                    Base64.encodeToString(
                        outputStream.toByteArray(),
                        Base64.DEFAULT
                    )

                val imageData =
                    "data:image/jpeg;base64,$base64Image"

                val request =
                    VisionRequest(

                        model =
                            "openai/gpt-4o-mini",

                        messages = listOf(

                            Message(

                                role = "user",

                                content = listOf(

                                    ContentPart(

                                        type = "text",

                                        text =
                                            "Identify this species and explain its ecological importance."
                                    ),

                                    ContentPart(

                                        type = "image_url",

                                        image_url =
                                            ImageUrl(imageData)
                                    )
                                )
                            )
                        )
                    )

                CoroutineScope(
                    Dispatchers.IO
                ).launch {

                    try {

                        val response =
                            RetrofitInstance.api
                                .generateVisionContent(request)

                        aiResult =
                            response.body()
                                ?.choices
                                ?.firstOrNull()
                                ?.message
                                ?.content
                                ?: "No AI response"

                    } catch (e: Exception) {

                        aiResult =
                            e.message.toString()
                    }

                    loading = false
                }
            }
        }

    if (showReportDialog) {

        AlertDialog(

            onDismissRequest = {

                showReportDialog = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        showReportDialog = false

                        Toast.makeText(

                            context,

                            "$selectedIssue reported successfully",

                            Toast.LENGTH_LONG

                        ).show()
                    }
                ) {

                    Text("Submit")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        showReportDialog = false
                    }
                ) {

                    Text("Cancel")
                }
            },

            title = {

                Text(
                    "Report Environmental Issue"
                )
            },

            text = {

                Column {

                    listOf(

                        "Illegal Tree Cutting",

                        "Waste Dumping",

                        "Water Pollution",

                        "Forest Fire",

                        "Wildlife Hunting",

                        "Land Encroachment"

                    ).forEach {

                        Row(

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            RadioButton(

                                selected =
                                    selectedIssue == it,

                                onClick = {

                                    selectedIssue = it
                                }
                            )

                            Text(it)
                        }
                    }
                }
            }
        )
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF4F6F3)
            )
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        Box {

            Image(

                painter =
                    painterResource(id = grove.image),

                contentDescription = null,

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(360.dp),

                contentScale =
                    ContentScale.Crop
            )

            Box(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .background(

                            Brush.verticalGradient(

                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.55f)
                                )
                            )
                        )
            )

            IconButton(

                onClick = {

                    onBack()
                },

                modifier =
                    Modifier
                        .padding(18.dp)
                        .background(
                            Color.White.copy(alpha = 0.18f),
                            RoundedCornerShape(14.dp)
                        )
            ) {

                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    tint = Color.White
                )
            }

            Column(

                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp)
            ) {

                Text(

                    text = grove.name,

                    color = Color.White,

                    fontSize = 36.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(

                    text = grove.description,

                    color =
                        Color.White.copy(alpha = 0.88f),

                    fontSize = 15.sp,

                    lineHeight = 22.sp
                )
            }
        }

        Column(

            modifier =
                Modifier.padding(20.dp)
        ) {

            InfoCard(

                title = "Cultural Importance",

                description = grove.myth,

                icon = {

                    Icon(
                        Icons.Default.Forest,
                        null,
                        tint = Color(0xFF2D6A4F)
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            InfoCard(

                title = "Scientific Importance",

                description = grove.science,

                icon = {

                    Icon(
                        Icons.Default.Science,
                        null,
                        tint = Color(0xFF2D6A4F)
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            InfoCard(

                title = "Biodiversity",

                description = grove.biodiversity,

                icon = {

                    Icon(
                        Icons.Default.WaterDrop,
                        null,
                        tint = Color(0xFF2D6A4F)
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            ActionButton(

                title = "Scan Species",

                icon = {

                    Icon(
                        Icons.Default.CameraAlt,
                        null,
                        tint = Color.White
                    )
                },

                color = Color(0xFF2D6A4F),

                onClick = {

                    cameraLauncher.launch(null)
                }
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            ActionButton(

                title = "Report Issue",

                icon = {

                    Icon(
                        Icons.Default.BugReport,
                        null,
                        tint = Color.White
                    )
                },

                color = Color(0xFFD62828),

                onClick = {

                    showReportDialog = true
                }
            )

            if (capturedBitmap != null) {

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Image(

                    painter =
                        BitmapPainter(
                            capturedBitmap!!.asImageBitmap()
                        ),

                    contentDescription = null,

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(
                                RoundedCornerShape(24.dp)
                            ),

                    contentScale =
                        ContentScale.Crop
                )
            }

            if (loading) {

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                CircularProgressIndicator(
                    color = Color(0xFF2D6A4F)
                )
            }

            if (aiResult.isNotEmpty()) {

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                Card(

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                Color.White
                        ),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),

                    shape =
                        RoundedCornerShape(24.dp)
                ) {

                    Text(

                        text = aiResult,

                        color = Color(0xFF1B4332),

                        modifier =
                            Modifier.padding(20.dp),

                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )
        }
    }
}

@Composable
fun ActionButton(

    title: String,

    icon: @Composable () -> Unit,

    color: Color,

    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier =
            Modifier
                .fillMaxWidth()
                .height(58.dp),

        colors =
            ButtonDefaults.buttonColors(
                containerColor = color
            ),

        shape =
            RoundedCornerShape(18.dp)
    ) {

        Row(

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            icon()

            Spacer(
                modifier =
                    Modifier.width(10.dp)
            )

            Text(
                title,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun InfoCard(

    title: String,

    description: String,

    icon: @Composable () -> Unit
) {

    Card(

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Row(

            modifier =
                Modifier.padding(18.dp)
        ) {

            Box(

                modifier =
                    Modifier
                        .size(50.dp)
                        .background(
                            Color(0xFFE8F3EC),
                            RoundedCornerShape(14.dp)
                        ),

                contentAlignment =
                    Alignment.Center
            ) {

                icon()
            }

            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )

            Column {

                Text(

                    text = title,

                    color = Color(0xFF1B4332),

                    fontSize = 18.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(

                    text = description,

                    color =
                        Color(0xFF4F5D52),

                    fontSize = 14.sp,

                    lineHeight = 22.sp
                )
            }
        }
    }
}