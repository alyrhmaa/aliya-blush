package com.example.aliya_blush.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityCustom1Binding
import com.google.android.material.snackbar.Snackbar

class Custom1_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding
    private val TAG = "Custom1_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Created")

        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerapkan Snackbar saat activity dibuka
        Snackbar.make(binding.root, "Selamat Datang di Custom 1", Snackbar.LENGTH_SHORT).show()

        binding.tvTitle.text = intent.getStringExtra("TITLE") ?: "Custom 1"
        binding.tvDesc.text = intent.getStringExtra("DESC") ?: "Deskripsi Default"

        // Menggunakan finish() untuk menghapus activity dari stack
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity Started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Destroyed")
    }
}
