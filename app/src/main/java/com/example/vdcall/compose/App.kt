package com.example.vdcall.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.Screen
import com.example.vdcall.compose.navigation.AppBottomNavBar
import com.example.vdcall.compose.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentSelectedScreen by navController.currentScreenAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            when{
                (currentRoute==Screen.Home.route||currentRoute==Screen.Room.route||currentRoute==Screen.Account.route)->
                    AppBottomNavBar(navController)
            }

        }
    ) {
        AppNavHost(navController,it)
    }
}
