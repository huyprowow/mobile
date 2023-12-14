package com.example.vdcall.compose.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vdcall.Screen
import com.example.vdcall.compose.screen.account.AccountScreen
import com.example.vdcall.compose.screen.authen.signup.SignUpScreen
import com.example.vdcall.compose.screen.authen.singin.SignInScreen
import com.example.vdcall.compose.screen.home.HomeScreen
import com.example.vdcall.compose.screen.roomdetail.RoomDetailScreen
import com.example.vdcall.compose.screen.roomlist.RoomScreen
import com.example.vdcall.viewmodels.Room.RoomListViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    it: PaddingValues
){
//    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = Screen.Room.route, modifier = Modifier.padding(it)) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(
            route = Screen.RoomDetail.route,
            arguments = Screen.RoomDetail.navArguments
        ) {
                navBackStackEntry ->
            /* Extracting the id from the route */
            val roomId = navBackStackEntry.arguments?.getString("roomId")
            val roomName = navBackStackEntry.arguments?.getString("roomName")
            /* We check if it's not null */
            roomId?.let { roomId->
                roomName?.let { roomName ->
                    RoomDetailScreen(
                        navController,
                        roomId,
                        roomName
    //               onShareClick = {
    //                    createShareIntent(activity, it)
    //               }
                    )
                }
            }

        }
        composable(
            route = Screen.Room.route,
        ) {
            RoomScreen( navController)
        }
        composable(
            route = Screen.SignIn.route,
        ) {
            SignInScreen()
        }
        composable(
            route = Screen.SignUp.route,
        ) {
            SignUpScreen()
        }
        composable(
            route = Screen.Account.route,
        ) {
           AccountScreen()
        }
    }
}

//private fun createShareIntent(activity: Activity, plantName: String) {
//    val shareText = activity.getString(R.string.share_text_plant, plantName)
//    val shareIntent = ShareCompat.IntentBuilder(activity)
//        .setText(shareText)
//        .setType("text/plain")
//        .createChooserIntent()
//        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//    activity.startActivity(shareIntent)
//}