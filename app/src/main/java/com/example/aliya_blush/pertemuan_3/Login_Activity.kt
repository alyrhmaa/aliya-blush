package com.example.aliya_blush.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityLoginBinding
import com.example.aliya_blush.pertemuan_4.Dashboard_Activity

class Login_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 LOGIN DINAMIS
            if (username == password) {

                val sp = getSharedPreferences("LOGIN", MODE_PRIVATE)
                val editor = sp.edit()

                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                startActivity(Intent(this, Dashboard_Activity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Username & Password harus sama!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}