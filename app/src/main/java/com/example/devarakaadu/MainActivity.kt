package com.example.devarakaadu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.devarakaadu.auth.LoginScreen
import com.example.devarakaadu.auth.RegisterScreen
import com.example.devarakaadu.data.Grove
import com.example.devarakaadu.database.AppDatabase
import com.example.devarakaadu.profile.ProfileScreen
import com.example.devarakaadu.session.SessionManager
import com.example.devarakaadu.ui.screens.DetailScreen
import com.example.devarakaadu.ui.screens.HomeScreen
import com.example.devarakaadu.ui.screens.SplashScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val sessionManager =
                SessionManager(this)

            val database =
                AppDatabase.getDatabase(this)

            var showSplash by remember {

                mutableStateOf(true)
            }

            var currentScreen by remember {

                mutableStateOf(

                    if (
                        sessionManager.isLoggedIn()
                    ) {

                        "home"

                    } else {

                        "login"
                    }
                )
            }

            var selectedGrove by remember {

                mutableStateOf<Grove?>(null)
            }

            var loggedInEmail by remember {

                mutableStateOf(

                    sessionManager.getUserEmail()
                )
            }

            val groves = listOf(

                Grove(

                    "Kodagu Sacred Grove",

                    "A culturally protected forest ecosystem in Kodagu known for rich biodiversity and sacred conservation traditions.",

                    "Local communities believe the grove is protected by forest deities. Rituals and spiritual practices prevent tree cutting and preserve nature.",

                    "The grove regulates groundwater, supports climate stability, prevents soil erosion, and conserves endemic Western Ghats flora.",

                    "Home to rare medicinal herbs, Malabar giant squirrels, exotic birds, butterflies, fungi, and native evergreen species.",

                    R.drawable.forest1
                ),

                Grove(

                    "Malenadu Kaavu",

                    "A dense rainforest sacred grove located in Karnataka’s Malenadu region with ancient ecological significance.",

                    "Villagers traditionally prohibit hunting and deforestation here believing the forest is spiritually sacred.",

                    "Acts as a major carbon sink, improves rainfall retention, maintains humidity, and supports ecological balance.",

                    "Contains orchids, amphibians, insects, reptiles, and endangered rainforest plant species.",

                    R.drawable.forest2
                ),

                Grove(

                    "Tulsi Sacred Patch",

                    "A traditional herbal conservation grove centered around medicinal and sacred Tulsi vegetation.",

                    "Tulsi plants are worshipped daily and considered symbols of purity, healing, and spiritual protection.",

                    "Tulsi naturally improves air quality and possesses antibacterial, antiviral, and medicinal properties.",

                    "Supports bees, butterflies, pollinators, medicinal shrubs, and micro-organisms essential for ecosystem health.",

                    R.drawable.forest3
                ),

                Grove(

                    "Banyan Heritage Grove",

                    "An ancient Banyan-dominated sacred ecosystem preserving centuries-old cultural and ecological heritage.",

                    "The massive banyan trees are believed to house ancestral spirits and are central to village rituals.",

                    "Banyan trees absorb large amounts of carbon dioxide and provide natural cooling and oxygen production.",

                    "Shelters birds, bats, monkeys, reptiles, insects, and numerous epiphytic plant species.",

                    R.drawable.forest4
                ),

                Grove(

                    "Western Ghats Grove",

                    "A biodiversity hotspot within the Western Ghats mountain ecosystem protected through sacred traditions.",

                    "Traditional customs strictly prohibit ecological destruction and encourage forest preservation practices.",

                    "Maintains ecological balance, protects watersheds, conserves endangered species, and stabilizes regional climate.",

                    "Contains endemic mammals, amphibians, medicinal plants, rare trees, and critically endangered wildlife.",

                    R.drawable.forest5
                )
            )

            if (showSplash) {

                SplashScreen(

                    onNavigate = {

                        showSplash = false
                    }
                )

            } else {

                when (currentScreen) {

                    "login" -> {

                        LoginScreen(

                            database = database,

                            onLoginSuccess = { email ->

                                loggedInEmail = email

                                sessionManager.saveLogin(email)

                                currentScreen = "home"
                            },

                            onRegisterClick = {

                                currentScreen = "register"
                            }
                        )
                    }

                    "register" -> {

                        RegisterScreen(

                            onRegister = { user ->

                                CoroutineScope(
                                    Dispatchers.IO
                                ).launch {

                                    database.userDao()
                                        .registerUser(user)
                                }

                                currentScreen = "login"
                            },

                            onBack = {

                                currentScreen = "login"
                            }
                        )
                    }

                    "home" -> {

                        HomeScreen(

                            groves = groves,

                            onGroveClick = {

                                selectedGrove = it

                                currentScreen = "detail"
                            },

                            onLogout = {

                                sessionManager.logout()

                                currentScreen = "login"
                            },

                            onProfileClick = {

                                currentScreen = "profile"
                            }
                        )
                    }

                    "detail" -> {

                        selectedGrove?.let {

                            DetailScreen(

                                grove = it,

                                onBack = {

                                    currentScreen = "home"
                                }
                            )
                        }
                    }

                    "profile" -> {

                        ProfileScreen(

                            userEmail = loggedInEmail,                            onBack = {

                                currentScreen = "home"
                            }
                        )
                    }
                }
            }
        }
    }
}