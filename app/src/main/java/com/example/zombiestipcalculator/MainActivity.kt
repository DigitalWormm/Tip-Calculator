package com.example.zombiestipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zombiestipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBTN.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        val str = binding.costOfServiceET.text.toString()
        val cst = str.toDoubleOrNull()
        if (cst == null){
            binding.tipResult.text = ""
            binding.costOfServiceET.text?.clear()
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedTip = binding.tipOptions.checkedRadioButtonId
        val roundUp = binding.roundUpSwitch.isChecked

        // Tip Percentage
        val res = when (selectedTip){
            R.id.optionTwentyPercent   -> 0.20
            R.id.optionEighteenPercent -> 0.18
            else                       -> 0.15
        }

        // TIP = RES * CST
        var tip = if (roundUp) ceil(res * cst) else res * cst
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_Result, formattedTip)
    }


}