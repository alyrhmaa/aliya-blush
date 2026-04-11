package com.example.aliya_blush.pertemuan_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityDashboardBinding
import com.example.aliya_blush.pertemuan_3.LoginActivity
import com.example.aliya_blush.pertemuan_2.MainActivity
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")

        binding.tvTitle.text = "Dashboard"
        binding.tvDesc.text = "Selamat datang, $username 👋"

        // Tombol 1 → Bangun ruang
        binding.btn1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("TITLE", "Rumus Bangun Ruang")
            intent.putExtra("DESC", "Hitung luas & volume")
            startActivity(intent)
        }

        // Tombol 2 → Custom 1
        binding.btn2.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("TITLE", "Custom 1")
            intent.putExtra("DESC", "Halaman custom pertama")
            startActivity(intent)
        }

        // Tombol 3 → Custom 2
        binding.btn3.setOnClickListener {
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("TITLE", "Custom 2")
            intent.putExtra("DESC", "Halaman custom kedua")
            startActivity(intent)
        }

        // Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Yakin mau logout?")
                .setPositiveButton("Ya") { _, _ ->
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}