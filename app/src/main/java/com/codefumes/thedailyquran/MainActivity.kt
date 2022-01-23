package com.codefumes.thedailyquran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.codefumes.thedailyquran.navigation.TheDailyQuranApp
import com.codefumes.thedailyquran.ui.theme.TheDailyQuranTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheDailyQuranTheme {
                TheDailyQuranApp()
            }
        }
    }
}

//@ExperimentalMaterial3Api
//@Composable
//fun TheDailyQuranApp() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "tasbeeh") {
//        composable("home") { HomePage(navController = navController) }
//        composable("tasbeeh") { TasbeehPage(navController = navController) }
//        composable("prayerTime") { PrayerTimePage(navController = navController) }
//    }
//}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheDailyQuranTheme {
      TheDailyQuranApp()
    }
}