package com.example.winlinetipo

import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.AppDatabase
import kotlinx.coroutines.launch

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val topUpButton = findViewById<Button>(R.id.topUpButton)

        topUpButton.setOnClickListener {

            val amount = amountEditText.text.toString().toDoubleOrNull()

            if (amount != null && amount >= 100) {
                lifecycleScope.launch {
                    val success = BalanceManager.topUp(amount)
                    if (success) {
                        Toast.makeText(this@TopUpActivity, "Баланс пополнен на $amount ₽", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@TopUpActivity, "Ошибка пополнения", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Минимум 100 ₽", Toast.LENGTH_SHORT).show()
            }
        }
    }
}