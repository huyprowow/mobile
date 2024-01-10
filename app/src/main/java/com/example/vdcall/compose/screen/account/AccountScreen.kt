package com.example.vdcall.compose.screen.account

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.edit
import com.example.vdcall.Activity.LoginActivity
import com.example.vdcall.Activity.SignUpActivity
import com.example.vdcall.Activity.dataStore
import com.example.vdcall.MainActivity
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

suspend fun clearDataStore(context: Context){
    context.dataStore.edit {
        it[EXAMPLE_COUNTER]=""
    }



}
@Composable
fun AccountScreen() {
    val scope= rememberCoroutineScope()
    val context= LocalContext.current;

    Column {
        Text(
            text = "Account"
        )
        Button(onClick = {
            scope.launch {
               clearDataStore(context)

                context.startActivity(Intent(context, LoginActivity::class.java).apply {})
            }
        }) {
            Text(
                text = "Đăng xuất"
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    VdcallTheme {
        AccountScreen()
    }
}