package com.example.winlinetipo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.data.AppDatabase
import kotlinx.coroutines.launch
import kotlin.math.roundToLong

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val balanceTextView = findViewById<TextView>(R.id.balanceTextView)
        val userId = intent.getIntExtra("userId", -1)
        val userDao = AppDatabase.getDatabase(this).userDao()

        lifecycleScope.launch {
            val bal = BalanceManager.getBalance()
            balanceTextView.text = "Баланс: ${bal.roundToLong()} ₽"
        }
        val addBalanceButton: ImageButton = findViewById(R.id.addBalanceButton)
        addBalanceButton.setOnClickListener {
            val intent = Intent(this, TopUpActivity::class.java)
            intent.putExtra("userId", userId) // ← передаём userId
            startActivity(intent)

        }
    }
    override fun onResume() {
        super.onResume()
        val balanceTextView = findViewById<TextView>(R.id.balanceTextView)

        lifecycleScope.launch {
            val balance = BalanceManager.getBalance()
            balanceTextView.text = "Баланс: ${balance.toInt()} ₽"
        }
    }
}