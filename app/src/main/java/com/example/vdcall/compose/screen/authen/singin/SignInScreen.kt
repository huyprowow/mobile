package com.example.vdcall.compose.screen.authen.singin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vdcall.ui.VdcallTheme
@Composable
fun SignInScreen(){
    Text(
        text = "SignIn"
    )
}
@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    VdcallTheme {
        SignInScreen()
    }
}