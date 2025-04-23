package com.example.tipxml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tipxml.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tipOptions = listOf("10%", "15%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipOptions)

        binding.spinner.adapter = adapter

        // Spinner item selected inline listener
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateTip()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // TextWatcher for input changes
        binding.etBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = updateTip()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Switch listener
        binding.switchRound.setOnCheckedChangeListener { _, _ -> updateTip() }
    }

    private fun updateTip() {
        val amount = binding.etBill.text.toString().toDoubleOrNull() ?: 0.0
        val tipText = binding.spinner.selectedItem.toString()
        val tipPercent = tipText.removeSuffix("%").toDoubleOrNull() ?: 15.0
        val roundUp = binding.switchRound.isChecked
        val tip = calculateTip(amount, tipPercent, roundUp)

        binding.tvTotalTip.text = getString(R.string.tip_amount, tip)
    }

    private fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = ceil(tip)
        }
        return NumberFormat.getCurrencyInstance().format(tip)
    }
}

