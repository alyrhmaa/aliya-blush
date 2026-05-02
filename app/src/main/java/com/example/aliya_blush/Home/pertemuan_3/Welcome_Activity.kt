package com.example.aliya_blush.Home.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")

        binding.tvWelcome.text = "Selamat Datang, $username 👋"
    }
}