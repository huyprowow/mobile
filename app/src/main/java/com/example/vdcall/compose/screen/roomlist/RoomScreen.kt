package com.example.vdcall.compose.screen.roomlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vdcall.ui.VdcallTheme
@Composable

fun RoomScreen() {
    Text("Room")
}

@Preview(showBackground = true)
@Composable
fun RoomPreview() {
    VdcallTheme {
        RoomScreen()
    }
}