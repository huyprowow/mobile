package com.example.vdcall.compose

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ShareCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.Screen
import com.example.vdcall.compose.navigation.AppBottomNavBar
import com.example.vdcall.compose.navigation.AppNavHost
import com.example.vdcall.compose.screen.authen.signup.SignUpScreen
import com.example.vdcall.compose.screen.authen.singin.SignInScreen
import com.example.vdcall.compose.screen.home.HomeScreen
import com.example.vdcall.compose.screen.roomdetail.RoomDetailScreen
import com.example.vdcall.compose.screen.roomlist.RoomScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){
    val navController = rememberNavController()
//    val currentSelectedScreen by navController.currentScreenAsState()
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if(currentRoute!=Screen.SignIn.route&&currentRoute!=Screen.SignUp.route)
            {
                AppBottomNavBar(navController)
            }
        }
    ) {
        AppNavHost(navController,it)
    }
}
