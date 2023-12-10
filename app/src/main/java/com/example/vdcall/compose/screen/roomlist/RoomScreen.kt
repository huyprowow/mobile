package com.example.vdcall.compose.screen.roomlist

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.Screen
import com.example.vdcall.compose.navigation.AppTopBar
import com.example.vdcall.data.repository.room.RoomRepository
import com.example.vdcall.dataStore
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import com.example.vdcall.viewmodels.Room.RoomListViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RoomScreen(navController: NavController,
               viewModel:RoomListViewModel= hiltViewModel()

) {
//    val context= LocalContext.current
//    var userName by remember { mutableStateOf("") }
//    var openJoinRoomDialog by remember { mutableStateOf(false) }
//    var openCreateRoomDialog by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//    LaunchedEffect(Unit) {
//        context.dataStore.data.map {
//            it[EXAMPLE_COUNTER] ?: ""
//        }.collect { value ->
//            userName = value
//        }
//
//
//    }
    val userName by viewModel.userName.observeAsState("")
    val openJoinRoomDialog by  viewModel.openJoinRoomDialog.observeAsState(false)
    val openCreateRoomDialog by  viewModel.openCreateRoomDialog.observeAsState(false)
    val rooms by viewModel.rooms.observeAsState()
    val scope= rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
        ) {
            stickyHeader{
                AppTopBar(navController,"Danh sách phòng của ${userName}", { actionIcon({viewModel.toggleJoinRoomDialog()},{viewModel.getAllRoom(userName)}) },"")
            }
            Log.d("Debug", "$rooms")

            rooms?.size?.let {
                items(count= it){index->
                    val room = rooms!![index % rooms!!.size]
                    ListItem(
                        headlineText = { Text(text=room.roomName, fontWeight = FontWeight.W700, fontSize = 17.sp) },
                        supportingText = { Text(room.roomDescription) },
                        leadingContent = {
                                Image(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "avatar", modifier = Modifier
                                    .size(32.dp)
                                    .clip(
                                        CircleShape
                                    ))
                        },
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.navigate(
                                        Screen.RoomDetail.createRoute(
                                            room._id
                                        )
                                    )
                                },
                            )
                            .padding(16.dp, 0.dp)
                            .clip(
                                CutCornerShape(
                                    topStart = 15.dp,
                                    topEnd = 10.dp,
                                    bottomEnd = 0.dp,
                                    bottomStart = 10.dp
                                )
                            ),
                        colors = ListItemDefaults.colors(containerColor=MaterialTheme.colorScheme.secondaryContainer),
                        shadowElevation = 2.dp,
                        tonalElevation = 2.dp
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
        FloatingActionButton(onClick = { viewModel.toggleCreateRoomDialog() },shape = CircleShape,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Join Room"
            )
        }
    }

    when {
        openJoinRoomDialog -> {
            JoinRoomDiaLog(
                onDismissRequest = { viewModel.toggleJoinRoomDialog()},
                onConfirmation = fun (roomName:String,roomPassword:String){
                    // Add logic here to handle confirmation.

                    scope.launch {
                         try{
                            viewModel.joinRoom(roomName,roomPassword,userName)
                            Log.d("Debug", "Join Room ${roomName},${roomPassword},${userName}")
                            }catch (error: Exception){
                                Log.d("Debug", "${error}")

                         }
                    }
                    viewModel.toggleJoinRoomDialog()
                },
                dialogTitle = "Join Room",
            )
        }
    }
    when {
        openCreateRoomDialog -> {
            CreateRoomDiaLog(
                onDismissRequest = {viewModel.toggleCreateRoomDialog()},
                onConfirmation = fun (roomName:String,roomPassword:String,roomDescription:String){
                    // Add logic here to handle confirmation.
                    scope.launch {
                        try {
                            val res= viewModel.createRoom(roomName,roomPassword,roomDescription,userName)
                            Log.d("Debug", "Confirmation registered ${roomName},${roomPassword},${roomDescription}")
                            Log.d("Debug", "${res}")
                        }catch (error: Exception){
                            Log.d("Debug", "${error}")
                        }
                    }
                    viewModel.toggleCreateRoomDialog()

                },
                dialogTitle = "Create Room",

            )
        }
    }
}
@Composable
fun actionIcon(onOpenJoinRoomDialog:()->Unit,reloadRoom:()->Unit){
    IconButton(
        onClick = {
            onOpenJoinRoomDialog()
        }
    ){
        Icon(
            imageVector = Icons.Filled.CoPresent,
            contentDescription = "Join Room"
        )
    }
    IconButton(
        onClick = {
            reloadRoom()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Reload Room"
        )
    }

}
//@Preview
//@Composable
//fun JoinRoomDiaLogPreview(){
//    JoinRoomDiaLog({ fun test() {} },{ fun test() {} },"title")
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinRoomDiaLog(
    onDismissRequest: () -> Unit,
    onConfirmation:  (roomName:String,roomPassword:String) -> Unit,
    dialogTitle: String,
) {
    var roomName by remember { mutableStateOf("") }
    var roomPassword by remember { mutableStateOf("") }
    AlertDialog(
        icon = {},
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column{
                Spacer(modifier = Modifier.size(2.dp))
                OutlinedTextField(
                    value = roomName,
                    onValueChange = { roomName = it },
                    label = { Text("Room Name") },
                    placeholder = { Text("Enter room name") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.size(2.dp))
                OutlinedTextField(
                    value = roomPassword,
                    onValueChange = { roomPassword = it },
                    label = { Text("Room Password") },
                    placeholder = { Text("Enter Password") },
                    singleLine = true
                )

            }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(roomName,roomPassword)
                }
            ) {
                Text("Join")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )

}
//@Preview
//@Composable
//fun CreateRoomDiaLogPreview(){
//    CreateRoomDiaLog({ fun test() {} },{ fun test() {} },"title")
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoomDiaLog(
    onDismissRequest: () -> Unit,
    onConfirmation: (roomName:String,roomPassword:String,roomDescription:String) -> Unit,
    dialogTitle: String,
) {
    var roomName by remember { mutableStateOf("") }
    var roomPassword by remember { mutableStateOf("") }
    var roomDescription by remember { mutableStateOf("") }
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismissRequest() }
    ){
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(
                        onClick = {
                            onDismissRequest()
                        }
                    ){
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close"
                        )
                    }
                    Text(text = dialogTitle)
                    TextButton(
                        onClick = {
                            onConfirmation(roomName,roomPassword,roomDescription)
                        }
                    ) {
                        Text("Create")
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxSize()
                ){
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = roomName,
                        onValueChange = { roomName = it },
                        label = { Text("Room Name") },
                        placeholder = { Text("Enter room name") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = roomPassword,
                        onValueChange = { roomPassword = it },
                        label = { Text("Room Password") },
                        placeholder = { Text("Enter Password") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        value = roomDescription,
                        onValueChange = { roomDescription = it },
                        label = { Text("Description") },
                        placeholder = { Text("Description") },
                        maxLines = 3
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun RoomPreview() {
    VdcallTheme {
        RoomScreen(rememberNavController())
//        CreateRoomDiaLog({ fun test() {} },fun  (roomName:String,roomPassword:String,roomDescription:String) {} ,"title")
//        JoinRoomDiaLog({ fun test() {} } , fun  (roomName: String, roomPassword: String) {} ,"title")
    }
}