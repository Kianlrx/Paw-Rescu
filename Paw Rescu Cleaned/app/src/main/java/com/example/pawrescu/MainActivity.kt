package com.example.pawrescu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pawrescu.ui.components.BottomNavBar
import com.example.pawrescu.ui.components.TopNavBar
import com.example.pawrescu.ui.screens.AdoptionInfoScreenContent
import com.example.pawrescu.ui.screens.DogDetailScreenContent
import com.example.pawrescu.ui.screens.DogListScreenContent
import com.example.pawrescu.ui.screens.HomeScreenContent
import com.example.pawrescu.ui.screens.SearchScreenContent
import com.example.pawrescu.ui.screens.UserProfileScreenContent
import com.example.pawrescu.ui.theme.PawRescuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawRescuTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopNavBar(
                            currentRoute = currentRoute,
                            onNavigateToHome = {
                                if (currentRoute != "home") {
                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            },
                            onNavigateToProfile = {
                                if (currentRoute != "user_profile") {
                                    navController.navigate("user_profile") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            onNavigateToHome = {
                                if (currentRoute != "home") {
                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            },
                            onNavigateToSearch = {
                                if (currentRoute != "search") {
                                    navController.navigate("search") {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            onNavigateToList = {
                                if (currentRoute != "dog_list") {
                                    navController.navigate("dog_list") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreenContent(
                                onFindDogClick = {
                                    navController.navigate("dog_list") {
                                        launchSingleTop = true
                                    }
                                },
                                onAdoptionInfoClick = {
                                    navController.navigate("adoption_info") {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("dog_list") {
                            DogListScreenContent(
                                onNavigateToDogDetail = { dogId ->
                                    navController.navigate("dog_detail/$dogId")
                                }
                            )
                        }

                        composable("search") {
                            SearchScreenContent(name = "Search")
                        }

                        composable("user_profile") {
                            UserProfileScreenContent(name = "User Profile")
                        }

                        composable("adoption_info") {
                            AdoptionInfoScreenContent(name = "Adoption Info")
                        }

                        composable(
                            route = "dog_detail/{dogId}",
                            arguments = listOf(navArgument("dogId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val dogId = backStackEntry.arguments?.getInt("dogId") ?: 1
                            DogDetailScreenContent(
                                dogId = dogId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
