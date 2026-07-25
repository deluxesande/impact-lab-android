package com.example.onlyfarmers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlyfarmers.ui.screens.AuthScreen
import com.example.onlyfarmers.ui.screens.SplashScreen
import com.example.onlyfarmers.ui.screens.consumer.AiChatScreen
import com.example.onlyfarmers.ui.screens.consumer.CartScreen
import com.example.onlyfarmers.ui.screens.consumer.HomeScreen
import com.example.onlyfarmers.ui.screens.consumer.TrackingScreen
import com.example.onlyfarmers.ui.screens.farmer.DashboardScreen
import com.example.onlyfarmers.ui.screens.farmer.EarningsScreen
import com.example.onlyfarmers.ui.screens.farmer.NewListingScreen
import com.example.onlyfarmers.ui.screens.farmer.OrdersScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(
            route = Screen.Auth.route,
            arguments = listOf(navArgument("role") { type = NavType.StringType }),
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "buyer"
            AuthScreen(navController = navController, role = role)
        }

        // Consumer
        composable(Screen.ConsumerHome.route) { HomeScreen(navController) }
        composable(Screen.AiChat.route) { AiChatScreen(navController) }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Tracking.route) { TrackingScreen(navController) }

        // Farmer
        composable(Screen.FarmerDashboard.route) { DashboardScreen(navController) }
        composable(Screen.NewListing.route) { NewListingScreen(navController) }
        composable(Screen.FarmerOrders.route) { OrdersScreen(navController) }
        composable(Screen.Earnings.route) { EarningsScreen(navController) }
    }
}
