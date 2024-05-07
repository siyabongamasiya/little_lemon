package android.capstone.littlelemon

import android.capstone.littlelemon.screens.Home
import android.capstone.littlelemon.screens.OnBoarding
import android.capstone.littlelemon.screens.Profile
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



@Composable
fun Navigation(navController: NavHostController,context : Context){
    val sharedPreferences = context.getSharedPreferences("loggin",Context.MODE_PRIVATE)

    val loggedin = sharedPreferences.getBoolean("loggedin?",false)
    val startdestination : String

    if (loggedin){
        startdestination = home.route
    }else {
        startdestination = onBoarding.route
    }

    NavHost(navController = navController, startDestination = startdestination){

        composable(onBoarding.route){
            OnBoarding(navController = navController)
        }

        composable(home.route){
            Home(navController)
        }

        composable(profile.route){
            Profile(navController)
        }
    }
}