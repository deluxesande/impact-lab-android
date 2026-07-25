package com.example.onlyfarmers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onlyfarmers.ui.navigation.NavGraph
import com.example.onlyfarmers.ui.theme.FarmersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FarmersTheme {
                NavGraph()
            }
        }
    }
}
