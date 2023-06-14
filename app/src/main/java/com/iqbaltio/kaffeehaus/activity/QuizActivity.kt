package com.iqbaltio.kaffeehaus.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityQuizBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class QuizActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQuizBinding
    private val prefViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.navigationBarColor = ContextCompat.getColor(this@QuizActivity, R.color.yellowish)
        window.statusBarColor = ContextCompat.getColor(this@QuizActivity, R.color.yellowish)

        binding.option1.setOnClickListener {
            binding.tvOption1.setText(R.string.option_1_vibe)
            playAnimation()
        }
        binding.option2.setOnClickListener {
            binding.tvOption1.setText(R.string.option_2_vibe)
            playAnimation()
        }
        binding.option3.setOnClickListener {
            binding.tvOption1.setText(R.string.option_3_vibe)
            playAnimation()
        }
        binding.option4.setOnClickListener {
            binding.tvOption1.setText(R.string.option_4_vibe)
            playAnimation()
        }
        binding.option21.setOnClickListener {
            binding.tvOption2.setText(R.string.option_2_1_utils)
            playAnimation2()
        }
        binding.option22.setOnClickListener {
            binding.tvOption2.setText(R.string.option_2_2_utils)
            playAnimation2()
        }
        binding.option23.setOnClickListener {
            binding.tvOption2.setText(R.string.option_2_3_utils)
            playAnimation2()
        }
        binding.option24.setOnClickListener {
            binding.tvOption2.setText(R.string.option_2_4_utils)
            playAnimation2()
        }
        binding.option31.setOnClickListener {
            binding.tvOption3.setText(R.string.option_3_1_view)
            playAnimation3()
        }
        binding.option32.setOnClickListener {
            binding.tvOption3.setText(R.string.option_3_2_view)
            playAnimation3()
        }
        binding.option33.setOnClickListener {
            binding.tvOption3.setText(R.string.option_3_3_view)
            playAnimation3()
        }
        binding.option34.setOnClickListener {
            binding.tvOption3.setText(R.string.option_3_4_view)
            playAnimation3()
        }
        binding.btnNext.setOnClickListener {
            storePreferensi()
        }
    }


    private fun playAnimation(){
        val option1Btn = ObjectAnimator.ofFloat(binding.option1, View.ALPHA, 0f).setDuration(500)
        val option2Btn = ObjectAnimator.ofFloat(binding.option2, View.ALPHA, 0f).setDuration(500)
        val option3Btn = ObjectAnimator.ofFloat(binding.option3, View.ALPHA, 0f).setDuration(500)
        val option4Btn = ObjectAnimator.ofFloat(binding.option4, View.ALPHA, 0f).setDuration(500)
        val tvOption1 = ObjectAnimator.ofFloat(binding.tvOption1, View.ALPHA, 1f).setDuration(800)
        binding.option21.visibility = View.VISIBLE
        val option21Btn = ObjectAnimator.ofFloat(binding.option21, View.ALPHA, 1f).setDuration(500)
        binding.option22.visibility = View.VISIBLE
        val option22Btn = ObjectAnimator.ofFloat(binding.option22, View.ALPHA, 1f).setDuration(500)
        binding.option23.visibility = View.VISIBLE
        val option23Btn = ObjectAnimator.ofFloat(binding.option23, View.ALPHA, 1f).setDuration(500)
        binding.option24.visibility = View.VISIBLE
        val option24Btn = ObjectAnimator.ofFloat(binding.option24, View.ALPHA, 1f).setDuration(500)
        val txtAmbienceQuest = ObjectAnimator.ofFloat(binding.QuizTextView, View.ALPHA, 0f).setDuration(500)
        val txtUtilsQuest = ObjectAnimator.ofFloat(binding.QuizTextView2, View.ALPHA, 1f).setDuration(500)
        AnimatorSet().apply {
            playSequentially(tvOption1,txtAmbienceQuest,option1Btn, option2Btn, option3Btn, option4Btn, txtUtilsQuest, option21Btn, option22Btn, option23Btn, option24Btn)
            start()
        }
    }

    private fun playAnimation2(){
        val option21Btn = ObjectAnimator.ofFloat(binding.option21, View.ALPHA, 0f).setDuration(500)
        val option22Btn = ObjectAnimator.ofFloat(binding.option22, View.ALPHA, 0f).setDuration(500)
        val option23Btn = ObjectAnimator.ofFloat(binding.option23, View.ALPHA, 0f).setDuration(500)
        val option24Btn = ObjectAnimator.ofFloat(binding.option24, View.ALPHA, 0f).setDuration(500)
        val tvOption2 = ObjectAnimator.ofFloat(binding.tvOption2, View.ALPHA, 1f).setDuration(800)
        binding.option31.visibility = View.VISIBLE
        val option31Btn = ObjectAnimator.ofFloat(binding.option31, View.ALPHA, 1f).setDuration(500)
        binding.option32.visibility = View.VISIBLE
        val option32Btn = ObjectAnimator.ofFloat(binding.option32, View.ALPHA, 1f).setDuration(500)
        binding.option33.visibility = View.VISIBLE
        val option33Btn = ObjectAnimator.ofFloat(binding.option33, View.ALPHA, 1f).setDuration(500)
        binding.option34.visibility = View.VISIBLE
        val option34Btn = ObjectAnimator.ofFloat(binding.option34, View.ALPHA, 1f).setDuration(500)
        val txtViewQuest = ObjectAnimator.ofFloat(binding.QuizTextView3, View.ALPHA, 1f).setDuration(500)
        val txtUtilsQuest = ObjectAnimator.ofFloat(binding.QuizTextView2, View.ALPHA, 0f).setDuration(500)
        AnimatorSet().apply {
            playSequentially(tvOption2, txtUtilsQuest, option21Btn, option22Btn, option23Btn, option24Btn, txtViewQuest, option31Btn, option32Btn, option33Btn, option34Btn)
            start()
        }
    }

    private fun playAnimation3(){
        val tvOption3 = ObjectAnimator.ofFloat(binding.tvOption3, View.ALPHA, 1f).setDuration(800)
        AnimatorSet().apply {
            playSequentially(tvOption3)
            start()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return
    }

    private fun storePreferensi(){
        loginViewModel.getUser().observe(this){ user ->
            val token = user.token
            val name = user.name
            val userId = user.id
            val tvOption1Text = binding.tvOption1.text.toString()
            val tvOption2Text = binding.tvOption2.text.toString()
            val tvOption3Text = binding.tvOption3.text.toString()
            prefViewModel.storePreferensi(token, name, tvOption1Text, tvOption2Text, tvOption3Text, userId).observe(this){ result ->
                when(result){
                    is Result.Success -> {
                        startActivity(Intent(this@QuizActivity, MainActivity::class.java))
                        Toast.makeText(this, result.data.message, Toast.LENGTH_SHORT).show()
                        Log.d("SuksesPref", result.data.toString())
                        finish()
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        Log.d("ErrorPref", "Error")
                    }
                }
            }
        }
    }
}