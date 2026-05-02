package com.example.aliya_blush.Home.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityCustom2Binding

class Custom2_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = intent.getStringExtra("TITLE")
        binding.tvDesc.text = intent.getStringExtra("DESC")
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}