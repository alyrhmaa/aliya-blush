package com.example.aliya_blush.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityLoginBinding
import com.example.aliya_blush.Home.pertemuan_4.Dashboard_Activity

class Login_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val spRegister = getSharedPreferences("USER_DATA", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val savedUsername = spRegister.getString("username", "")
            val savedPassword = spRegister.getString("password", "")

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // RULE 1
            if (username == password) {

                startActivity(Intent(this, Dashboard_Activity::class.java))
                finish()

            }
            // RULE 2
            else if (username == savedUsername && password == savedPassword) {

                startActivity(Intent(this, Dashboard_Activity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Login gagal!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
        }
    }
}