package com.example.onlyfarmers.ui.navigation

sealed class Screen(val route: String) {
    // Shared
    object Splash : Screen("splash")
    object Auth : Screen("auth/{role}") {
        fun createRoute(role: String) = "auth/$role"
    }

    // Consumer
    object ConsumerHome : Screen("consumer/home")
    object AiChat : Screen("consumer/ai_chat")
    object Cart : Screen("consumer/cart")
    object Tracking : Screen("consumer/tracking")
    object Search : Screen("consumer/search")
    object ConsumerOrders : Screen("consumer/orders")
    object Account : Screen("consumer/account")
    object ProductDetail : Screen("consumer/product/{name}") {
        fun createRoute(name: String) = "consumer/product/$name"
    }

    // Farmer
    object FarmerProfile : Screen("farmer/profile")

    // Farmer
    object FarmerDashboard : Screen("farmer/dashboard")
    object NewListing : Screen("farmer/new_listing")
    object FarmerOrders : Screen("farmer/orders")
    object Earnings : Screen("farmer/earnings")
}
