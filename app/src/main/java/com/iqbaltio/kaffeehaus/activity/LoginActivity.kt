package com.iqbaltio.kaffeehaus.activity

import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.data.api.LoginRequest
import com.iqbaltio.kaffeehaus.data.api.UserModel
import com.iqbaltio.kaffeehaus.databinding.ActivityLoginBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edEmail.doOnTextChanged { text, _, _, _ ->
            if (text?.contains(("@")) != true){
                binding.EmailInputLayout.error = "Email format must be valid"
            }else if (text.contains(("@"))){
                binding.EmailInputLayout.error = null
            }
        }

        binding.edPassword.doOnTextChanged { text, _, _, _ ->
            if(text!!.length < 8){
                binding.PasswordInputLayout.error = "Password still less than 8 character"
            }else if(text.length >= 8){
                binding.PasswordInputLayout.error = null
            }
        }

        binding.txtRegis.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        fadeInAnimation()

        binding.btnLogin.setOnClickListener{
            storeDataLogin()
        }

        binding.btnGoogle.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun storeDataLogin(){
        val responseData = LoginRequest(binding.edEmail.text.toString(), binding.edPassword.text.toString())
        loginViewModel.Login(responseData).observe(this) { result ->
            when(result){
                is Result.Success -> {
                    val responseLogin = result.data.loginResult
                    loginViewModel.storeUser(
                        UserModel(
                            responseLogin?.id.toString(),
                            responseLogin?.name.toString(),
                            responseLogin?.token.toString(),
                            true
                        )
                    )
                    Toast.makeText(this, result.data.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, QuizActivity::class.java))
                    finish()
                }
                is Result.Loading -> { Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show() }
                is Result.Error -> {
                    Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Something went wrong" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fadeInAnimation() {

        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        val logTagline = binding.txtTagline
        val login = binding.txtLogin
        val logEmail = binding.EmailInputLayout
        val logPassword = binding.PasswordInputLayout
        val btnLogin = binding.btnLogin
        val btnGoogle = binding.btnGoogle
        val regis = binding.txtRegis

        AnimatorSet().apply {
            logTagline.startAnimation(animation)
            login.startAnimation(animation)
            logEmail.startAnimation(animation)
            logPassword.startAnimation(animation)
            btnLogin.startAnimation(animation)
            btnGoogle.startAnimation(animation)
            regis.startAnimation(animation)
        }
    }

}