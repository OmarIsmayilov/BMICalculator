package com.example.bmicalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.bmicalculator.databinding.ActivityResultBinding
import kotlin.properties.Delegates

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var bmi by Delegates.notNull<Double>()
    private lateinit var userList : ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.navigationBarColor = Color.parseColor("#2d4057")
        window.statusBarColor = Color.parseColor("#2d4057")
        userList = ArrayList()

        bmi = intent.getStringExtra("bmi").toString().toDouble()
        setTexts()

        binding.buttonRestart.setOnClickListener {
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




    }

    @SuppressLint("ResourceAsColor")
    private fun setTexts() {
        binding.tvBmi.text = bmi.toString()
        if (bmi<=18.5){
            binding.tvBmi.setTextColor(Color.parseColor("#0F3FCF"))
            binding.tvTitle.text = "Underweight "
            binding.tvDescription.text = "This BMI value is below the healthy range, indicating that the body weight is too low. This condition can lead to health issues and affect body functions. To gain more weight, plan a healthy diet and exercise program."
        } else if(bmi in 18.6..24.9){
            binding.tvBmi.setTextColor(Color.parseColor("#36FF3F"))
            binding.tvTitle.text = "Normal"
            binding.tvDescription.text = "This BMI value is within the healthy range, indicating that the body weight is appropriate for the person's height. Keep up a healthy lifestyle to maintain this weight."
        }else if(bmi in 25.0..29.9){
            binding.tvBmi.setTextColor(Color.parseColor("#EAD205"))
            binding.tvTitle.text = "Overweight"
            binding.tvDescription.text = "This BMI value indicates that the person is carrying extra weight, which can increase the risk of health problems such as heart disease, high blood pressure, and type 2 diabetes. To lose weight, start a healthy diet and exercise program."
        }else if(bmi in 30.0..39.9){
            binding.tvBmi.setTextColor(Color.parseColor("#EC4611"))
            binding.tvTitle.text = "Obese"
            binding.tvDescription.text = "This BMI value indicates that the person is significantly overweight and at a higher risk for health problems such as heart disease, high blood pressure, type 2 diabetes, and sleep apnea. To improve health, consult a doctor and start a weight loss program."
        }else if(bmi >= 40.0){
            binding.tvBmi.setTextColor(Color.parseColor("#DF0A0A"))
            binding.tvTitle.text = "Morbidly Obese"
            binding.tvDescription.text = "This BMI value is a severe form of obesity, indicating that the person is at a very high risk for health problems such as heart disease, high blood pressure, type 2 diabetes, sleep apnea, and even certain cancers. To improve health and reduce these risks, consult a doctor and start a medically supervised weight loss program."
        }

    }
}