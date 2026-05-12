package com.example.devarakaadu.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.example.devarakaadu.data.Grove
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@SuppressLint("MissingPermission")

@Composable
fun MapScreen(

    groves: List<Grove>,

    onBack: () -> Unit
) {

    val context = LocalContext.current

    var userLocation by remember {

        mutableStateOf<GeoPoint?>(null)
    }

    val permissionLauncher =

        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.RequestPermission()

        ) { granted ->

            if (granted) {

                val fusedLocationClient =

                    LocationServices
                        .getFusedLocationProviderClient(context)

                fusedLocationClient
                    .lastLocation
                    .addOnSuccessListener { location ->

                        if (location != null) {

                            userLocation = GeoPoint(

                                location.latitude,

                                location.longitude
                            )
                        }
                    }
            }
        }

    LaunchedEffect(Unit) {

        if (

            ContextCompat.checkSelfPermission(

                context,

                Manifest.permission.ACCESS_FINE_LOCATION

            ) == PackageManager.PERMISSION_GRANTED

        ) {

            val fusedLocationClient =

                LocationServices
                    .getFusedLocationProviderClient(context)

            fusedLocationClient
                .lastLocation
                .addOnSuccessListener { location ->

                    if (location != null) {

                        userLocation = GeoPoint(

                            location.latitude,

                            location.longitude
                        )
                    }
                }

        } else {

            permissionLauncher.launch(

                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        AndroidView(

            modifier = Modifier.fillMaxSize(),

            factory = { context ->

                Configuration.getInstance().load(

                    context,

                    PreferenceManager
                        .getDefaultSharedPreferences(context)
                )

                MapView(context).apply {

                    layoutParams =
                        ViewGroup.LayoutParams(

                            ViewGroup.LayoutParams.MATCH_PARENT,

                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                    setTileSource(
                        TileSourceFactory.MAPNIK
                    )

                    setMultiTouchControls(true)
                }
            },

            update = { mapView ->

                mapView.overlays.clear()

                mapView.controller.setZoom(8.0)

                val centerPoint =

                    userLocation ?: GeoPoint(

                        12.9716,

                        77.5946
                    )

                mapView.controller.setCenter(centerPoint)

                groves.forEach { grove ->

                    val marker = Marker(mapView)

                    marker.position = GeoPoint(

                        grove.latitude,

                        grove.longitude
                    )

                    marker.title = grove.name

                    marker.subDescription =
                        "Tap for directions"

                    marker.setOnMarkerClickListener { _, _ ->

                        val uri = Uri.parse(

                            "google.navigation:q=${grove.latitude},${grove.longitude}"
                        )

                        val intent = Intent(

                            Intent.ACTION_VIEW,

                            uri
                        )

                        intent.setPackage(
                            "com.google.android.apps.maps"
                        )

                        context.startActivity(intent)

                        true
                    }

                    mapView.overlays.add(marker)
                }

                userLocation?.let {

                    val userMarker = Marker(mapView)

                    userMarker.position = it

                    userMarker.title =
                        "You are here"

                    mapView.overlays.add(userMarker)
                }

                mapView.invalidate()
            }
        )

        IconButton(

            onClick = {

                onBack()
            },

            modifier = Modifier
                .padding(18.dp)
                .size(52.dp)
                .background(

                    Color.White.copy(alpha = 0.96f),

                    RoundedCornerShape(16.dp)
                )
                .align(Alignment.TopStart)
        ) {

            Icon(

                imageVector =
                    Icons.AutoMirrored.Filled.ArrowBack,

                contentDescription = null,

                tint = Color(0xFF1B4332)
            )
        }
    }
}