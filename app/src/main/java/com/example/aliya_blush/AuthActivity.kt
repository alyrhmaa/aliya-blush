package com.example.aliya_blush

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityAuthBinding
import com.example.aliya_blush.Home.pertemuan_2.MainActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences
        val sharedPref =
            getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )

        // Cek Status Login
        val isLogin =
            sharedPref.getBoolean(
                "isLogin",
                false
            )

        // Jika sudah login langsung ke MainActivity
        if (isLogin) {

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            startActivity(intent)
            finish()
        }

        // BUTTON LOGIN
        binding.btnLogin.setOnClickListener {

            val username =
                binding.etUsername.text.toString()

            val password =
                binding.etPassword.text.toString()

            // Validasi Login
            if (
                username.isNotEmpty() &&
                password.isNotEmpty() &&
                username == password
            ) {

                // Simpan Status Login
                val editor = sharedPref.edit()

                editor.putBoolean("isLogin", true)
                editor.putString("username", username)

                editor.apply()

                // Toast Berhasil
                Toast.makeText(
                    this,
                    "Login Berhasil! Selamat Datang $username 👋",
                    Toast.LENGTH_SHORT
                ).show()

                // Pindah ke MainActivity
                val intent = Intent(
                    this,
                    MainActivity::class.java
                )

                startActivity(intent)
                finish()

            } else {

                // Alert Login Gagal
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage(
                        "Username atau Password salah.\nSilakan coba lagi."
                    )

                    .setPositiveButton("Tutup", null)
                    .show()
            }
        }
    }
}