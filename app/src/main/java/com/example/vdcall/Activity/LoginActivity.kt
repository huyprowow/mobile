package com.example.vdcall.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.vdcall.MainActivity
import com.example.vdcall.api.authen.LoginService
import com.example.vdcall.data.repository.authen.LoginRepository
import com.example.vdcall.dataStore
import com.example.vdcall.databinding.ActivityLoginBinding
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesDataStore")
class LoginActivity
 : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

        private val loginRepository: LoginRepository= LoginRepository(LoginService.create())

    lateinit var username : EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    lateinit var register:TextView
    var scope= CoroutineScope(newSingleThreadContext("name"))
    suspend fun checkUserNameDatastore() {

        dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: ""
        }.collect { value ->
            if (value != "") {
                startActivity(Intent(this, MainActivity::class.java).apply {})

            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        scope.launch{
            checkUserNameDatastore()
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            Log.d("debug","${binding.username.text.toString()} ")
            scope.launch {
                try{
                   var x= loginRepository.login(binding.username.text.toString(),binding.password.text.toString())
                    if(x!=null){
                        Log.d("Debug", "userName: $x")
                        dataStore.edit {
                            it[EXAMPLE_COUNTER]=binding.username.text.toString()
                        }
                      checkUserNameDatastore()
                    }else{
                        Toast.makeText(applicationContext, "Login Failed!", Toast.LENGTH_SHORT).show()
                    }
                }catch (error: Exception){

                    Log.d("Debug", "$error")
                }
            }
        })
        binding.register.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java).apply {})
        })
    }
}