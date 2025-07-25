package com.example.winlinetipo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.data.AppDatabase
import com.example.data.MainActivity
import com.example.data.UserDao
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        db = AppDatabase.getDatabase(this)
        userDao = db.userDao()

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            lifecycleScope.launch {
                val user = userDao.getUserByEmail(email)
                if (user != null) {
                    startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Неверные данные", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun openRegisterActivity(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}