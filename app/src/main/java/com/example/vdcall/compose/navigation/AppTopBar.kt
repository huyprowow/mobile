package com.example.vdcall.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.ui.VdcallTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController, title:String, actions:@Composable ()->Unit ){
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        actions={
            actions()
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview(){
    VdcallTheme {
        AppTopBar(rememberNavController(),"Home",
            {x()}
        )
    }
}

@Composable
fun x(){
    IconButton(
        onClick = {  }
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Localized description"
        )
    }

}