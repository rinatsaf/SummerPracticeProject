package com.example.winlinetipo.model

data class Bet(
    val id: Int,
    val title: String,
    val amount: Double,
    val odds: Double,
    val description: String
)