package com.example.winlinetipo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToLong

class PlaceBetActivity : AppCompatActivity() {

    private lateinit var bet: Bet
    private var amount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_bet)

        bet = intent.getParcelableExtra(EXTRA_BET) ?: return

        val tvTitle: TextView = findViewById(R.id.tvTitlePlace)
        val etAmount: EditText = findViewById(R.id.etAmount)
        val tvPotential: TextView = findViewById(R.id.tvPotential)
        val btnConfirm: Button = findViewById(R.id.btnConfirm)

        tvTitle.text = bet.title

        etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                amount = s.toString().toDoubleOrNull() ?: 0.0
                updatePotential(tvPotential)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnConfirm.setOnClickListener {
            if (amount <= 0) {
                Toast.makeText(this, "Введите сумму ставки", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = BalanceManager.deduct(amount)
            if (success) {
                Toast.makeText(
                    this,
                    "Пари заключено! Потенциальный выигрыш: ${calcPotentialBet().roundToLong()}",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            } else {
                Toast.makeText(this, "Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcPotentialBet(): Double = amount * bet.odds

    private fun updatePotential(tv: TextView) {
        tv.text = String.format("Потенциальный выигрыш: %.2f", calcPotentialBet())
    }

    companion object {
        const val EXTRA_BET = "extra_bet"
    }
}