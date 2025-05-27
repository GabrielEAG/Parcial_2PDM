package com.pdmcourse.spotlyfe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.pdmcourse.spotlyfe.ui.navigation.MainNavigation
import com.pdmcourse.spotlyfe.ui.theme.SpotLyfeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      SpotLyfeTheme {
        val navController = rememberNavController()
        MainNavigation(navController = navController)
      }
    }
  }
}