package com.example.vdcall.compose.screen.roomdetail

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.compose.navigation.AppTopBar
import com.example.vdcall.compose.screen.roomlist.actionIcon
import com.example.vdcall.ui.VdcallTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoomDetailScreen(navController: NavController, roomId:String, roomName:String){
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
        ) {
            stickyHeader {
                AppTopBar(
                    navController,
                    "Phòng ${roomName}",
                    {
                    },
                    "",
                    backClick=true
                )
            }   

//            rooms?.size?.let {
//                items(count = it) { index ->
//                    val room = rooms!![index % rooms!!.size]
//                    ListItem(
//                        headlineText = { Text(room.roomName) },
//                        supportingText = { Text(room.roomDescription) },
//                        leadingContent = {
//                            Column {
//                                Image(
//                                    imageVector = Icons.Filled.Person,
//                                    contentDescription = "avatar",
//                                    modifier = Modifier
//                                        .size(32.dp)
//                                        .clip(
//                                            CircleShape
//                                        )
//                                )
//                                Text(text = userName)
//                            }
//
//                        },
//
//                    )
//                }
//            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
        ) {
            BasicTextField(value = "", onValueChange = {}) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomDetailPreview() {
    VdcallTheme {
        RoomDetailScreen( rememberNavController(),"123","123")
    }
}
