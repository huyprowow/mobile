package com.example.vdcall.compose.screen.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vdcall.ui.VdcallTheme
@Composable
fun AccountScreen() {
    Text(
        text = "Account"
    )
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    VdcallTheme {
        AccountScreen()
    }
}