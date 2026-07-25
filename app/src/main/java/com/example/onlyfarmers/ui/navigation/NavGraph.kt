package com.example.onlyfarmers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlyfarmers.ui.screens.AuthScreen
import com.example.onlyfarmers.ui.screens.SplashScreen
import com.example.onlyfarmers.ui.screens.consumer.AccountScreen
import com.example.onlyfarmers.ui.screens.consumer.AiChatScreen
import com.example.onlyfarmers.ui.screens.consumer.CartScreen
import com.example.onlyfarmers.ui.screens.consumer.ConsumerOrdersScreen
import com.example.onlyfarmers.ui.screens.consumer.HomeScreen
import com.example.onlyfarmers.ui.screens.consumer.ProductDetailScreen
import com.example.onlyfarmers.ui.screens.consumer.SearchScreen
import com.example.onlyfarmers.ui.screens.consumer.TrackingScreen
import com.example.onlyfarmers.ui.screens.farmer.DashboardScreen
import com.example.onlyfarmers.ui.screens.farmer.EarningsScreen
import com.example.onlyfarmers.ui.screens.farmer.FarmerProfileScreen
import com.example.onlyfarmers.ui.screens.farmer.NewListingScreen
import com.example.onlyfarmers.ui.screens.farmer.OrdersScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    var lang by remember { mutableStateOf("EN") }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController = navController, lang = lang, onLangChange = { lang = it })
        }

        composable(
            route = Screen.Auth.route,
            arguments = listOf(navArgument("role") { type = NavType.StringType }),
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "buyer"
            AuthScreen(navController = navController, role = role, lang = lang, onLangChange = { lang = it })
        }

        // Consumer
        composable(Screen.ConsumerHome.route) { HomeScreen(navController, lang, { lang = it }) }
        composable(Screen.AiChat.route) { AiChatScreen(navController, lang, { lang = it }) }
        composable(Screen.Cart.route) { CartScreen(navController, lang, { lang = it }) }
        composable(Screen.Tracking.route) { TrackingScreen(navController, lang, { lang = it }) }
        composable(Screen.Search.route) { SearchScreen(navController, lang, { lang = it }) }
        composable(Screen.ConsumerOrders.route) { ConsumerOrdersScreen(navController, lang, { lang = it }) }
        composable(Screen.Account.route) { AccountScreen(navController, lang, { lang = it }) }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType }),
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ProductDetailScreen(navController, name, lang, { lang = it })
        }

        // Farmer
        composable(Screen.FarmerDashboard.route) { DashboardScreen(navController, lang, { lang = it }) }
        composable(Screen.NewListing.route) { NewListingScreen(navController, lang, { lang = it }) }
        composable(Screen.FarmerOrders.route) { OrdersScreen(navController, lang, { lang = it }) }
        composable(Screen.Earnings.route) { EarningsScreen(navController, lang, { lang = it }) }
        composable(Screen.FarmerProfile.route) { FarmerProfileScreen(navController, lang, { lang = it }) }
    }
}
