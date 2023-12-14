package com.example.vdcall.compose.screen.authen.singin

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.vdcall.Activity.AutherActivity
import com.example.vdcall.ui.VdcallTheme
@Composable
fun SignInScreen(){
    val mContext= LocalContext.current
    mContext.startActivity(Intent(mContext, AutherActivity::class.java))
}
@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    VdcallTheme {
        SignInScreen()
    }
}