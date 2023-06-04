package com.iqbaltio.kaffeehaus

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.doOnTextChanged
import com.iqbaltio.kaffeehaus.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

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