package com.example.winlinetipo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.winlinetipo.model.Bet

class BetViewModel : ViewModel() {
    private val _bets = MutableLiveData<MutableList<Bet>>(mutableListOf())
    val bets: LiveData<MutableList<Bet>> = _bets

    fun addBet(bet: Bet) {
        _bets.value?.add(bet)
        _bets.value = _bets.value
    }

    fun getBetById(id: Int): Bet? {
        return _bets.value?.find { it.id == id }
    }
}