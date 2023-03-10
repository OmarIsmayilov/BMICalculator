package com.example.bmicalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var weight = 0.0
    private var height = 0.0
    private var bmi = ""
    private var gender = "Male"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.navigationBarColor = Color.parseColor("#2d4057")
        window.statusBarColor = Color.parseColor("#2d4057")
        binding.cdBoy.setBackgroundResource(R.drawable.card_selected_bg)
        binding.ivBoy.setImageResource(R.drawable.ic_boy_blur)



        binding.btnCalculate.setOnClickListener {
            if (height == 0.0) {
                val snackbar =
                    Snackbar.make(binding.root, "Choose your height", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.parseColor("#C8F0DC"))
                snackbar.setTextColor(Color.parseColor("#048A4B"))
                snackbar.show()
                return@setOnClickListener
            }
            if (weight == 0.0) {
                val snackbar =
                    Snackbar.make(binding.root, "Choose your weight", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.parseColor("#C8F0DC"))
                snackbar.setTextColor(Color.parseColor("#048A4B"))
                snackbar.show()
                return@setOnClickListener
            }
            binding.btnCalculate.startAnimation()
            calculateBmi()
        }

        binding.etHeight.addTextChangedListener {
            if (it.toString().isNotEmpty() && it.toString().toFloat() in 50.0..250.0) {
                binding.sbHeight.value = it.toString().toFloat()
                height = it.toString().toDouble()
            } else {
                height = 0.0
            }
        }
        binding.etWeight.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                weight = it.toString().toDouble()
            }
        }

        binding.sbHeight.addOnChangeListener { slider, value, fromUser ->
            binding.etHeight.setText(value.toInt().toString())
            height = value.toDouble()
        }


        binding.btnInc.setOnClickListener {
            weight += 1
            binding.etWeight.setText(weight.toInt().toString())
        }
        binding.btnDec.setOnClickListener {
            if (weight > 0) {
                weight -= 1
                binding.etWeight.setText(weight.toInt().toString())
            }

        }


        binding.cdBoy.setOnClickListener {
            cardBoy()

        }
        binding.cdGirl.setOnClickListener {
            cardGirl()
        }


    }


    private fun cardBoy() {
        binding.cdBoy.setBackgroundResource(R.drawable.card_selected_bg)
        binding.cdGirl.setBackgroundResource(R.drawable.card_bg2)
        binding.ivBoy.setImageResource(R.drawable.ic_boy_blur)
        binding.ivGirl.setImageResource(R.drawable.ic_girl)
        gender = "Male"
    }

    private fun cardGirl() {
        binding.cdGirl.setBackgroundResource(R.drawable.card_selected_bg)
        binding.cdBoy.setBackgroundResource(R.drawable.card_bg2)
        binding.ivBoy.setImageResource(R.drawable.ic_boy)
        binding.ivGirl.setImageResource(R.drawable.ic_girl_blur)
        gender = "Female"
    }


    private fun calculateBmi() {
        height = binding.etHeight.text.toString().toDouble()
        weight = binding.etWeight.text.toString().toDouble()
        bmi = "%3.1f".format(User(gender, weight, height).calculateBMI())



        Handler().postDelayed({
            binding.btnCalculate.revertAnimation {
                binding.btnCalculate.text = "Show"
            }
        }, 1500)

        binding.btnCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
            finish()
        }

    }



}