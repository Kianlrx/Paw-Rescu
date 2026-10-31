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
import com.example.pawrescu.ui.screens.SearchResultsScreenContent
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
                            SearchScreenContent(
                                onSearchClick = { name, location, size, gender, breed, age ->
                                    navController.navigate("search_results?name=$name&location=$location&size=$size&gender=$gender&breed=$breed&age=$age") {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(
                            route = "search_results?name={name}&location={location}&size={size}&gender={gender}&breed={breed}&age={age}",
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType; defaultValue = "" },
                                navArgument("location") { type = NavType.StringType; defaultValue = "" },
                                navArgument("size") { type = NavType.StringType; defaultValue = "" },
                                navArgument("gender") { type = NavType.StringType; defaultValue = "" },
                                navArgument("breed") { type = NavType.StringType; defaultValue = "" },
                                navArgument("age") { type = NavType.StringType; defaultValue = "" }
                            )
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name") ?: ""
                            val location = backStackEntry.arguments?.getString("location") ?: ""
                            val size = backStackEntry.arguments?.getString("size") ?: ""
                            val gender = backStackEntry.arguments?.getString("gender") ?: ""
                            val breed = backStackEntry.arguments?.getString("breed") ?: ""
                            val age = backStackEntry.arguments?.getString("age") ?: ""

                            SearchResultsScreenContent(
                                nameQuery = name,
                                locationQuery = location,
                                sizeQuery = size,
                                genderQuery = gender,
                                breedQuery = breed,
                                ageQuery = age,
                                onNavigateToDogDetail = { dogId ->
                                    navController.navigate("dog_detail/$dogId")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("user_profile") {
                            UserProfileScreenContent()
                        }

                        composable("adoption_info") {
                            AdoptionInfoScreenContent()
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
