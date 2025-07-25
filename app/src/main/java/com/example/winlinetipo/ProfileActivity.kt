package com.example.winlinetipo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val balanceTextView = findViewById<TextView>(R.id.balanceTextView)

        balanceTextView.text = "Баланс: 1000 ₽"
        val addBalanceButton: ImageButton = findViewById(R.id.addBalanceButton)
        addBalanceButton.setOnClickListener {
            val intent = Intent(this, TopUpActivity::class.java)
            startActivity(intent)
        }
    }
}