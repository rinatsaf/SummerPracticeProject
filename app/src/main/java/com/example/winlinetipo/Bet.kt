package com.example.winlinetipo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bet(
    val title: String,
    val odds: Double
) : Parcelable