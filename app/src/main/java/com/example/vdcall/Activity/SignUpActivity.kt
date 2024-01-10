package com.example.vdcall.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import com.example.vdcall.MainActivity
import com.example.vdcall.api.authen.LoginService
import com.example.vdcall.api.authen.RegisterService
import com.example.vdcall.data.repository.authen.LoginRepository
import com.example.vdcall.data.repository.authen.RegisterRepository
import com.example.vdcall.databinding.ActivityLoginBinding
import com.example.vdcall.databinding.ActivitySignupBinding
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext


class SignUpActivity : AppCompatActivity() {
    private val registerRepository: RegisterRepository = RegisterRepository(RegisterService.create())
    private lateinit var binding: ActivitySignupBinding
    suspend fun checkUserNameDatastore() {

        dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: ""
        }.collect { value ->
            if (value != "") {
                startActivity(Intent(this, MainActivity::class.java).apply {})

            }
        }

    }

    lateinit var signupUser : EditText
    lateinit var signupPassword: EditText
    lateinit var signupButton: Button
    var scope= CoroutineScope(newSingleThreadContext("name"))
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener(View.OnClickListener {
            Log.d("debug","${binding.signupUser.text.toString()} ")
            scope.launch {
                try{
                    var x= registerRepository.register(binding.signupUser.text.toString(),binding.signupPassword.text.toString())
                    if(x!=null){
                        Log.d("Debug", "userName: $x")
                        dataStore.edit {
                            it[EXAMPLE_COUNTER]=binding.signupUser.text.toString()
                        }
                        checkUserNameDatastore()
                    }else{
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Signup Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (error: Exception){
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Signup Failed!", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("Debug", "$error")
                }
            }
        })
        binding.signin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java).apply {})
        })
    }
}