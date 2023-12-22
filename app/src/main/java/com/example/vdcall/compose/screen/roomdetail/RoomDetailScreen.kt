package com.example.vdcall.compose.screen.roomdetail

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.Screen
import com.example.vdcall.compose.navigation.AppTopBar
import com.example.vdcall.compose.screen.roomlist.actionIcon
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.viewmodels.Room.RoomDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailScreen(navController: NavController, roomId:String, roomName:String,
                     viewModel:RoomDetailViewModel= hiltViewModel()){
    val scope= rememberCoroutineScope()
    val chats by viewModel.chats.observeAsState()
    val message by viewModel.message.observeAsState("")
    val userName by viewModel.userName.observeAsState()
    val scrollState=rememberLazyListState()
    Scaffold(
        topBar = {
            AppTopBar(
                rememberNavController(),
                "Phòng ${roomName}",
                {
                },
                "",
                backClick=true
            )
        })
        {paddingValue->
            Column(modifier = Modifier.padding(paddingValue)){
                Box(modifier = Modifier.weight(1f)) {
                    LazyColumn(
                        reverseLayout = true,
                        modifier = Modifier.fillMaxSize(),
                        state =scrollState
                    ) {
//                        stickyHeader {
//                        }
                        val reveseChat=chats?.reversed()
                        reveseChat?.size?.let {
                            items(count = it) { index ->

                                val chat = reveseChat!![index % reveseChat!!.size]
                                Row(
                                    modifier = Modifier
                                        .padding( start=10.dp,end=10.dp,top= 5.dp, bottom = 0.dp)
//                                        .fillMaxWidth()
                                    ,
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    if (chat.user.userName != userName) {
                                        Column {
                                            Image(
                                                imageVector = Icons.Filled.Person,
                                                contentDescription = "avatar",
                                                modifier = Modifier
                                                    .size(32.dp)
                                                    .clip(
                                                        CircleShape
                                                    )
                                            )
                                            Text(text = if(chat.user.userName.length>=5) chat.user.userName.substring(0, 3)+"..." else chat.user.userName)
                                        }
                                    }
                                    ListItem(
                                        headlineText = {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = if (chat.user.userName == userName) Arrangement.End else Arrangement.Start
                                            ) {
                                                Text(
                                                    chat.chatMessage,
                                                    textAlign = if (chat.user.userName == userName) TextAlign.End else TextAlign.Start,
                                                    fontSize = 18.sp
                                                )
                                            }
                                        },
                                        supportingText = {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = if (chat.user.userName == userName) Arrangement.End else Arrangement.Start
                                            ) {
                                                Text(chat.dateCreated,fontSize = 10.sp)
                                            }
                                          },
                                        modifier = Modifier
                                            .padding( start=10.dp,end=0.dp,top= 5.dp, bottom = 0.dp)
                                            .clip(
                                                if (chat.user.userName == userName) RoundedCornerShape(
                                                    topStart = 25.dp,
                                                    topEnd = 25.dp,
                                                    bottomEnd = 0.dp,
                                                    bottomStart = 25.dp
                                                ) else RoundedCornerShape(
                                                    topStart = 25.dp,
                                                    topEnd = 25.dp,
                                                    bottomEnd = 25.dp,
                                                    bottomStart = 0.dp
                                                )
                                            ),
                                        colors = ListItemDefaults.colors(
                                            containerColor = if (chat.user.userName == userName)
                                                MaterialTheme.colorScheme.secondaryContainer
                                            else
                                                MaterialTheme.colorScheme.tertiaryContainer
                                        ),
                                        shadowElevation = 2.dp,
                                        tonalElevation = 2.dp

                                    )
                                }
                            }
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(value = message, onValueChange = {
                        viewModel.onChangeMessage(it)

                    }, modifier = Modifier
                        .fillMaxWidth(0.9f)
                    )
                    IconButton(onClick = {
                        userName?.let {
                            scope.launch {
                                viewModel.chatMessage(roomId,message, it) }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Send"
                        )
                    }
                }
            }


        }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun RoomDetailPreview() {
    VdcallTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    rememberNavController(),
                    "Phòng ",
                    {
                    },
                    "",
                    backClick=true
                )
            })
        {paddingValue->
        Column(modifier = Modifier.padding(paddingValue)){
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    reverseLayout = true,
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState()
                ) {
                    items(10) { index ->
                        Row(
                            modifier = Modifier
                                .padding( start=10.dp,end=10.dp,top= 5.dp, bottom = 0.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if(index!=0){
                                Column {
                                    Image(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = "avatar",
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(
                                                CircleShape
                                            )
                                    )
                                    Text(text = "user")
                                }
                            }
                            ListItem(
                                headlineText = { Text("${index}",textAlign = if(index!=0) TextAlign.Start else TextAlign.End  ) },
                                supportingText = { Text("${index}") },
                                modifier = Modifier
                                    .padding( start=10.dp,end=0.dp,top= 5.dp, bottom = 0.dp)
                                    .clip(
                                        if (index != 0) RoundedCornerShape(
                                            topStart = 25.dp,
                                            topEnd = 25.dp,
                                            bottomEnd = 25.dp,
                                            bottomStart = 0.dp
                                        ) else RoundedCornerShape(
                                            topStart = 25.dp,
                                            topEnd = 25.dp,
                                            bottomEnd = 0.dp,
                                            bottomStart = 25.dp
                                        )
                                    ),
                                colors = ListItemDefaults.colors(
                                    containerColor= if (index!=0)
                                        MaterialTheme.colorScheme.secondaryContainer
                                    else
                                        MaterialTheme.colorScheme.secondaryContainer
                                ),
                                shadowElevation = 2.dp,
                                tonalElevation = 2.dp

                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(value = "", onValueChange = {
                }, modifier = Modifier.padding(0.dp)
                    .fillMaxWidth(0.9f)
                )
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            }
        }
    }

//        RoomDetailScreen( rememberNavController(),"123","123")
    }
}
