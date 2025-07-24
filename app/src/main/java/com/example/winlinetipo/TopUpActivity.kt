package com.example.winlinetipo

import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val topUpButton = findViewById<Button>(R.id.topUpButton)

        topUpButton.setOnClickListener {
            val amountText = amountEditText.text.toString()
            val amount = amountText.toIntOrNull()

            if (amount != null && amount >= 100) {
                // TODO: Реализовать пополнение (например, обновить баланс или сделать API-запрос)
                Toast.makeText(this, "Баланс пополнен на $amount ₽", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Минимум 100 ₽", Toast.LENGTH_SHORT).show()
            }
        }
    }
}