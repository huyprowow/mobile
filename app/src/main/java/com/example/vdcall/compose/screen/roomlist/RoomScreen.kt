package com.example.vdcall.compose.screen.roomlist

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.compose.navigation.AppTopBar
import com.example.vdcall.dataStore
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.flow.map

fun getUser(){

}
@Composable

fun RoomScreen(navController: NavController) {
    val context= LocalContext.current
    var userName by remember { mutableStateOf("") }
    var openAlertDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        context.dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: ""
        }.collect { value ->
            userName = value
        }
    }
    AppTopBar(navController,"Danh sách phòng", { actionIcon({openAlertDialog=true}) })
    when {
        openAlertDialog -> {
            JoinRoomDiaLog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Join Room"
            )
        }
    }
}
@Composable
fun actionIcon(onClick:()->Unit){
    IconButton(
        onClick = {
           onClick()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Localized description"
        )
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinRoomDiaLog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
) {
    var roomName by remember { mutableStateOf("") }
    var roomPassword by remember { mutableStateOf("") }
    var roomDescription by remember { mutableStateOf("") }
    AlertDialog(
        icon = {

        },
        title = {
            Text(text = dialogTitle)
        },
        text = {

                Column(modifier = Modifier.padding(10.dp,0.dp)){
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

//                    OutlinedTextField(
//
//                        value = roomDescription,
//                        onValueChange = { roomDescription = it },
//                        label = { Text("Description") },
//                        placeholder = { Text("Description") },
//                        singleLine = true
//                    )
                }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
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

@Preview(showBackground = true)
@Composable
fun RoomPreview() {
    VdcallTheme {
        RoomScreen(rememberNavController())
    }
}