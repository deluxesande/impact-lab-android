package com.example.onlyfarmers.ui.navigation

sealed class Screen(val route: String) {
    // Shared
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object LocationPicker : Screen("location_picker")
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
    object Checkout : Screen("consumer/checkout")
    object OrderDetail : Screen("consumer/order/{ref}") {
        fun createRoute(ref: String) = "consumer/order/$ref"
    }
    object Notifications : Screen("consumer/notifications")

    // Farmer
    object FarmerProfile : Screen("farmer/profile")
    object FarmerNotifications : Screen("farmer/notifications")
    object FarmerListings : Screen("farmer/listings")
    object RateHistory : Screen("farmer/rate_history")

    // Shared utility
    object HelpFAQ : Screen("help_faq")

    // Farmer
    object FarmerDashboard : Screen("farmer/dashboard")
    object NewListing : Screen("farmer/new_listing")
    object FarmerOrders : Screen("farmer/orders")
    object Earnings : Screen("farmer/earnings")
}
