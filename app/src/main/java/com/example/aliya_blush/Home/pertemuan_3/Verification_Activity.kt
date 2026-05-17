package com.example.aliya_blush.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                    .setTitle("Error")
                    .setMessage("OTP salah atau kosong!")
                    .setPositiveButton("OK", null)
                    .show()

            } else {

                // SIMPAN DATA
                val sp = getSharedPreferences("USER_DATA", MODE_PRIVATE)
                val editor = sp.edit()

                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                startActivity(Intent(this, Login_Activity::class.java))
                finish()
            }
        }
    }
}