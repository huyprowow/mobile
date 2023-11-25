package com.example.vdcall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vdcall.compose.App
import com.example.vdcall.ui.VdcallTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VdcallTheme {
                App()
            }
        }
    }
}