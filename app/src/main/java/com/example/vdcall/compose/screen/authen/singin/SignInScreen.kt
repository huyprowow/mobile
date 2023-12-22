package com.example.vdcall.compose.screen.authen.singin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.Screen
import com.example.vdcall.dataStore
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController:NavController){
    val userName = remember {
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()
    val context= LocalContext.current
    suspend fun checkUserNameDatastore() {

        context.dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: ""
        }.collect { value ->
            if (value != "") {
                navController.navigate(Screen.Room.route)
            }
        }

    }
    suspend fun setUserNameDatastore(userName:String){
        context.dataStore.edit{
            it[EXAMPLE_COUNTER]=userName
        }
    }
    // Safely update the current lambdas when a new one is provided
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

//    Toast.makeText(
//        context,
//        "DisposableEffectWithLifeCycle composition ENTER",
//        Toast.LENGTH_SHORT
//    ).show()

//    val currentOnResume by rememberUpdatedState(onResume)
//    val currentOnPause by rememberUpdatedState(onPause)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
//                    Toast.makeText(
//                        context,
//                        "DisposableEffectWithLifeCycle ON_CREATE",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    scope.launch{
                        checkUserNameDatastore()
                    }
                }
                Lifecycle.Event.ON_START -> {

                }
                Lifecycle.Event.ON_RESUME -> {

                }
                Lifecycle.Event.ON_PAUSE -> {

                }
                Lifecycle.Event.ON_STOP -> {

                }
                Lifecycle.Event.ON_DESTROY -> {

                }
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)

//            Toast.makeText(
//                context,
//                "DisposableEffectWithLifeCycle composition EXIT",
//                Toast.LENGTH_SHORT
//            )
//                .show()
        }
    }
    Column {
        OutlinedTextField(value = userName.value , onValueChange = {
                it->userName.value=it
        })
        Button(onClick = {
            scope.launch {
                setUserNameDatastore(userName.value)
                navController.navigate(Screen.Room.route)
            }
        }) {
            Text(text = "=>")
        }
    }

}
@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    VdcallTheme {
        SignInScreen(rememberNavController())
    }
}