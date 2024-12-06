package com.example.hiddencalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDiffBTN: Button
    private lateinit var buttonDivBTN: Button
    private lateinit var buttonMultBTN: Button

    private lateinit var resultTV: TextView

    private var result = 0.0

    private lateinit var sendDataBTN: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)
        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDiffBTN = findViewById(R.id.buttonDiffBTN)
        buttonDivBTN = findViewById(R.id.buttonDivBTN)
        buttonMultBTN = findViewById(R.id.buttonMultBTN)
        sendDataBTN = findViewById(R.id.sendDataBTN)
        resultTV = findViewById(R.id.resultTV)

        buttonSumBTN.setOnClickListener(this)
        buttonDiffBTN.setOnClickListener(this)
        buttonDivBTN.setOnClickListener(this)
        buttonMultBTN.setOnClickListener(this)

        sendDataBTN.setOnClickListener {
            if (result == 0.0) return@setOnClickListener
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", result)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {

        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()) return

        var first = firstOperandET.text.toString().toDouble()
        var second = secondOperandET.text.toString().toDouble()

        result = when (v?.id) {
            R.id.buttonSumBTN -> Operation(first,second).sum()
            R.id.buttonDiffBTN -> Operation(first,second).diff()
            R.id.buttonDivBTN -> Operation(first,second).div()
            R.id.buttonMultBTN -> Operation(first,second).mult()
            else -> 0.0
        }
        resultTV.text = result.toString()
    }
}