package com.example.aliya_blush.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityCustom1Binding

class Custom1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = intent.getStringExtra("TITLE")
        binding.tvDesc.text = intent.getStringExtra("DESC")
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}