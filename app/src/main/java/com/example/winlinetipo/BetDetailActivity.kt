package com.example.winlinetipo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BetDetailActivity : AppCompatActivity() {

    private lateinit var bet: Bet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bet_detail)

        bet = intent.getParcelableExtra(EXTRA_BET) ?: return

        val tvTitle: TextView = findViewById(R.id.tvTitleDetail)
        val tvOdds: TextView = findViewById(R.id.tvOdds)
        val btnPlaceBet: Button = findViewById(R.id.btnPlaceBet)

        tvTitle.text = bet.title
        tvOdds.text = String.format("Коэффициент: %.2f", bet.odds)

        btnPlaceBet.setOnClickListener {
            val intent = Intent(this, PlaceBetActivity::class.java).apply {
                putExtra(PlaceBetActivity.EXTRA_BET, bet)
            }
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_BET = "extra_bet"
    }
}