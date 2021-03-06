package com.codefumes.thedailyquran.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codefumes.thedailyquran.layout.MainLayout
import com.codefumes.thedailyquran.pages.*
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
fun TheDailyQuranApp() {
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {

        composable(route = Screen.Prayer.route) {
            PrayerTimePage(navController = navController)
        }
        composable(route = Screen.Qiblah.route){
            QiblahPage(navController = navController)
        }
        navigation(startDestination = Screen.Home.route, route = Screen.HomeView.route){
            composable(route = Screen.Home.route) {
                HomePage(navController = navController)
            }
            composable(route = Screen.SurahView.route) {
                SurahView(navController = navController)
            }
            composable(route = Screen.AsmaUlHusna.route){
                AsmaUlHusnaPage(navController = navController)
            }
            composable(route = Screen.QuranView.route + "?surahNo={surahNo}",
                arguments = listOf(navArgument("surahNo") { defaultValue = "1" })
            )
            { backStackEntry ->
                QuranView(
                    navController = navController,
                    surahNo = Integer.parseInt(backStackEntry.arguments?.getString("surahNo"))
                )
            }
            composable(route = Screen.SupplicationsView.route) {
                SupplicationsPage(navController = navController)
            }
            composable(
                route = Screen.SingleSupplicationsView.route + "?supplicationId={supplicationId}",
                arguments = listOf(navArgument("supplicationId") { defaultValue = "1" })
            ) { backStackEntry ->
                SingleSupplicationPage(
                    navController = navController,
                    supplicationIndex = Integer.parseInt(backStackEntry.arguments?.getString("supplicationId"))
                )
            }
        }
        composable(route = Screen.About.route){
            AboutPage(navController = navController)
        }
        navigation(startDestination = Screen.TasbeehGoals.route, route = Screen.TasbeehView.route) {
            composable(route = Screen.TasbeehGoals.route) {
                TasbeehPage(navController = navController)
            }
            composable(
                route = Screen.TasbeehCounter.route,
                arguments = listOf(navArgument("goalID") { type = NavType.IntType })
            ) { backStackEntry ->
                TasbeehCounterPage(
                    navController = navController,
                    goalID = backStackEntry.arguments?.getInt("goalID")
                )
            }

        }
    }
}