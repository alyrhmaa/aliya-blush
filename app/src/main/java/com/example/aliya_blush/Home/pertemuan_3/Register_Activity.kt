package com.example.aliya_blush.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityRegisterBinding

class Register_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnSubmit.setOnClickListener {

            val nama = binding.etNama.text.toString()
            val phone = binding.etPhone.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (nama.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Verification_Activity::class.java)
            intent.putExtra("phone", phone)
            intent.putExtra("username", username)
            intent.putExtra("password", password)

            startActivity(intent)
        }
    }
}