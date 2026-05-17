package com.example.aliya_blush

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityAuthBinding
import com.example.aliya_blush.Home.pertemuan_9.NinthActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // CEK LOGIN
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            startActivity(Intent(this, NinthActivity::class.java))
            finish()
        }

        // BUTTON LOGIN
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && username == password) {

                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(
                    this,
                    "Login Berhasil! Selamat Datang $username 👋",
                    Toast.LENGTH_SHORT
                ).show()

                // 🔥 PINDAH KE NINTH ACTIVITY
                startActivity(Intent(this, NinthActivity::class.java))
                finish()

            } else {

                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah.\nSilakan coba lagi.")
                    .setPositiveButton("Tutup", null)
                    .show()
            }
        }
    }
}