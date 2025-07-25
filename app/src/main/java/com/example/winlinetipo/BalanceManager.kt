package com.example.winlinetipo

import android.content.Context
import com.example.data.AppDatabase
import com.example.data.User
import com.example.data.UserDao

object BalanceManager {
    private lateinit var userDao: UserDao
    private var userId: Int = -1

    fun init(context: Context, loggedInUserId: Int) {
        userId = loggedInUserId
        userDao = AppDatabase.getDatabase(context.applicationContext).userDao()
    }

    suspend fun getBalance(): Double {
        val user: User? = userDao.getUserById(userId)
        return user?.balance ?: 0.0
    }

    suspend fun topUp(amount: Double): Boolean {
        val user = userDao.getUserById(userId) ?: return false
        val updated = user.copy(balance = user.balance + amount)
        userDao.update(updated)
        return true
    }

    suspend fun deduct(amount: Double): Boolean {
        val user = userDao.getUserById(userId) ?: return false
        return if (user.balance >= amount) {
            val updated = user.copy(balance = user.balance - amount)
            userDao.update(updated)
            true
        } else {
            false
        }
    }
}