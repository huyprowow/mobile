package com.example.vdcall

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object RoomDetail : Screen(
        route = "roomDetail/{roomId}/{roomName}",
        navArguments = listOf(
            navArgument("roomId") {
            type = NavType.StringType
        },
            navArgument("roomName") {
                type = NavType.StringType
            }
        )
    ) {
        fun createRoute(roomId: String,roomName:String) = "roomDetail/${roomId}/${roomName}"
    }
    data object Room : Screen("room")
    data object SignIn : Screen("signIn")
    data object SignUp : Screen("signUp")
    data object Account : Screen("Account")

}