package com.example.vdcall.compose.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.vdcall.ui.VdcallTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController, title:String, actions:@Composable ()->Unit,ImgSrc:String?,backClick:Boolean=false){

    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (backClick) Arrangement.Start else Arrangement.SpaceBetween

                ) {
                when{
                    backClick->
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                }){
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIos,
                                contentDescription = "Localized description",

                            )
                        }
                }
                when{
                    ImgSrc != ""-> AsyncImage(
                        model =ImgSrc , contentDescription = "avatar", modifier = Modifier
                            .size(32.dp)
                            .clip(
                                CircleShape
                            )
                    )
                }
                Text(title)
            }
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
        AppTopBar(rememberNavController(),

            "Danh Sach phong cua huy",
            {x()},
            "",
//            "https://nomination-jewellery.com.au/cdn/shop/products/030201_07-01_1024x1024@2x.jpg?v=1589771529",
        backClick=true
        )
    }
}

@Composable
fun x(){
//    IconButton(
//        onClick = {  }
//    ){
//        Icon(
//            imageVector = Icons.Filled.Add,
//            contentDescription = "Localized description"
//        )
//    }
//    IconButton(
//        onClick = {  }
//    ){
//        Icon(
//            imageVector = Icons.Filled.Add,
//            contentDescription = "Localized description"
//        )
//    }
}