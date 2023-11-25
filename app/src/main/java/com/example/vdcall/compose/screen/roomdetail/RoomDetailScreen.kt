package com.example.vdcall.compose.screen.roomdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vdcall.ui.VdcallTheme

@Composable
fun RoomDetailScreen(){
    Text(
        text = "RoomDetail",
    )
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    VdcallTheme {
        RoomDetailScreen()
    }
}
