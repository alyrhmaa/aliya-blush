package com.example.aliya_blush.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityCustom2Binding
import com.google.android.material.snackbar.Snackbar

class Custom2_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding
    private val TAG = "Custom2_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Created")

        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerapkan Snackbar
        Snackbar.make(binding.root, "Halaman Custom 2 Siap", Snackbar.LENGTH_LONG)
            .setAction("OK") { }
            .show()

        binding.tvTitle.text = intent.getStringExtra("TITLE") ?: "Custom 2"
        binding.tvDesc.text = intent.getStringExtra("DESC") ?: "Ini adalah halaman custom kedua."

        // Menggunakan finish() untuk menghapus activity dari stack
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
