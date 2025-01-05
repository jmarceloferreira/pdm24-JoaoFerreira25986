package com.example.vinylstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vinylstore.ui.navigation.AppNavHost
import com.example.vinylstore.ui.theme.VinylStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinylStoreTheme {
                AppNavHost()
            }
        }
    }
}
