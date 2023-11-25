package com.example.vdcall.compose.screen.home

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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Text(
        text = "Hello Home",
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    VdcallTheme {
        HomeScreen()
    }
}