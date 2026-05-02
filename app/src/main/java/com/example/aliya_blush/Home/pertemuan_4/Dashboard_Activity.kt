package com.example.aliya_blush.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.aliya_blush.databinding.ActivityDashboardBinding
import com.example.aliya_blush.Home.pertemuan_2.Kalkulator_Bangun
import com.example.aliya_blush.Home.pertemuan_3.Login_Activity
import com.example.aliya_blush.Home.pertemuan_6.WebView_Activity

class Dashboard_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR
        val toolbar = findViewById<Toolbar>(binding.toolbar.id)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sp = getSharedPreferences("LOGIN", MODE_PRIVATE)
        val username = sp.getString("username", "")

        binding.tvDesc.text = "Selamat Datang, $username 👋"

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, Kalkulator_Bangun::class.java))
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this, Custom1_Activity::class.java))
        }

        binding.btn3.setOnClickListener {
            startActivity(Intent(this, Custom2_Activity::class.java))
        }

        binding.btnWeb.setOnClickListener {
            startActivity(Intent(this, WebView_Activity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Yakin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    sp.edit().clear().apply()
                    startActivity(Intent(this, Login_Activity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}