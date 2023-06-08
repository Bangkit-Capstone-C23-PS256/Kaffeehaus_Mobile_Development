package com.iqbaltio.kaffeehaus.activity

import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityRegisterBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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

        fadeInAnimation()

        binding.btnRegister.setOnClickListener{
            sendUserData()
        }

    }

    private fun sendUserData() {
        val username = binding.edUsername.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()

        if(username.isEmpty() && email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Fill all first", Toast.LENGTH_SHORT).show()
            return
        }else if(password.length < 8){
            Toast.makeText(this, "Password doesn't have 8 character", Toast.LENGTH_SHORT).show()
            return
        }else if(username.isNotEmpty() && email.isNotEmpty() && password.length >= 8){
            registerViewModel.Register(username, email, password).observe(this){ user ->
                when(user){
                    is Result.Success -> {
                        Toast.makeText(this, user.data.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading.....", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, user.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun fadeInAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        val regTagline = binding.txtRegTagline
        val register = binding.txtRegister
        val regUsername = binding.UsernameInputLayout
        val regEmail = binding.EmailInputLayout
        val regPassword = binding.PasswordInputLayout
        val btnRegis = binding.btnRegister

        AnimatorSet().apply {
            regTagline.startAnimation(animation)
            register.startAnimation(animation)
            regUsername.startAnimation(animation)
            regEmail.startAnimation(animation)
            regPassword.startAnimation(animation)
            btnRegis.startAnimation(animation)
        }
    }
}