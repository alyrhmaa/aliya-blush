package com.example.aliya_blush.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.databinding.ActivityVerificationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Verification_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val phone = intent.getStringExtra("phone") ?: ""
        val username = intent.getStringExtra("username") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        binding.btnVerify.setOnClickListener {
            val otp = binding.etOtp.text.toString()

            if (otp.isEmpty() || otp != phone) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Verifikasi Gagal")
                    .setMessage("Kode OTP salah! Gunakan nomor HP Anda sebagai kode OTP.")
                    .setPositiveButton("Coba Lagi", null)
                    .show()
            } else {
                // SIMPAN DATA KE PREFERENCES UNTUK LOGIN
                val sp = getSharedPreferences("USER_DATA", MODE_PRIVATE)
                val editor = sp.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                // LANGSUNG KE AUTH ACTIVITY (LOGIN UTAMA)
                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}
