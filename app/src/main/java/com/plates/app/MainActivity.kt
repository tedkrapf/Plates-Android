package com.plates.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plates.app.ui.screens.ActiveTripScreen
import com.plates.app.ui.screens.CreateTripScreen
import com.plates.app.ui.screens.HomeScreen
import com.plates.app.ui.screens.TripDetailsScreen
import com.plates.app.ui.theme.PlatesTheme
import com.plates.app.viewmodel.PlatesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlatesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val viewModel: PlatesViewModel = viewModel(factory = PlatesViewModel.Factory)

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("create_trip") {
                            CreateTripScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(
                            route = "active_trip/{tripId}",
                            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val tripId = backStackEntry.arguments!!.getLong("tripId")
                            ActiveTripScreen(
                                navController = navController,
                                viewModel = viewModel,
                                tripId = tripId
                            )
                        }
                        composable(
                            route = "trip_details/{tripId}",
                            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val tripId = backStackEntry.arguments!!.getLong("tripId")
                            TripDetailsScreen(
                                navController = navController,
                                viewModel = viewModel,
                                tripId = tripId
                            )
                        }
                    }
                }
            }
        }
    }
}
