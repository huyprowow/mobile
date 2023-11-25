package com.example.vdcall.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.R
import com.example.vdcall.Screen
import com.example.vdcall.ui.VdcallTheme


@Composable
fun AppBottomNavBar(navController: NavHostController){
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute==Screen.Home.route,
            onClick = { navController.navigate( Screen.Home.route)},
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.ic_home_description))
            },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_home_24px),
                    contentDescription = stringResource(id = R.string.ic_home_description)
                )
            }
//            modifier
        )
        NavigationBarItem(
            selected =  currentRoute==Screen.Room.route,
            onClick = { navController.navigate(Screen.Room.route) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.ic_chat_description))
            },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_chat_bubble_24px),
                    contentDescription = stringResource(id = R.string.ic_chat_description)
                )
            }
        )
        NavigationBarItem(
            selected =  currentRoute==Screen.Account.route,
            onClick = { navController.navigate(Screen.Account.route) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.ic_manage_account_description))
            },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_manage_accounts_24px),
                    contentDescription = stringResource(id = R.string.ic_manage_account_description)
                )
            }
        )

        
    }
}
@Preview
@Composable
fun AppBottomNavBarPreview() {
    VdcallTheme {
        AppBottomNavBar(rememberNavController())
    }
}
