package com.example.winlinetipo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val btnProfile: FloatingActionButton = findViewById(R.id.btnProfile)

        val bets = listOf(
            Bet("Реал Мадрид — Барселона", 1.85),
            Bet("Манчестер Сити — Ливерпуль", 2.10),
            Bet("Бавария — Боруссия Д", 1.95)
        )

        adapter = BetAdapter(bets) { bet ->
            val intent = Intent(this, BetDetailActivity::class.java).apply {
                putExtra(BetDetailActivity.EXTRA_BET, bet)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnProfile.setOnClickListener {
            startActivity(Intent("com.example.winlinetipo.ProfileActivity"))
        }
    }
}