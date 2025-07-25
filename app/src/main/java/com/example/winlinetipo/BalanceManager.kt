package com.example.winlinetipo

/**
 * Временная заглушка для управления балансом пользователя.
 * Должна быть заменена на реальную реализацию другого разработчика.
 */
object BalanceManager {
    var balance: Double = 1000.0

    fun deduct(amount: Double): Boolean {
        return if (balance >= amount) {
            balance -= amount
            true
        } else {
            false
        }
    }
}