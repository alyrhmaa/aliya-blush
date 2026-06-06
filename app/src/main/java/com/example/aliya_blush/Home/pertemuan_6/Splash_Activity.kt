package com.example.aliya_blush.Home.pertemuan_6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.BaseActivity
import com.example.aliya_blush.R
import com.example.aliya_blush.onboarding.OnBoardingActivity

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            
            // 1. Cek apakah Onboarding sudah pernah diselesaikan
            val onboardingSelesai = getSharedPreferences("onboarding", Context.MODE_PRIVATE)
                .getBoolean("selesai", false)

            if (!onboardingSelesai) {
                // Jika belum pernah onboarding, ke halaman Onboarding
                startActivity(Intent(this, OnBoardingActivity::class.java))
            } else {
                // 2. Jika sudah onboarding, cek status login
                val isLogin = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    .getBoolean("isLogin", false)

                if (isLogin) {
                    // Jika sudah login, ke Home (BaseActivity)
                    startActivity(Intent(this, BaseActivity::class.java))
                } else {
                    // Jika belum login, ke halaman Auth/Login
                    startActivity(Intent(this, AuthActivity::class.java))
                }
            }
            
            finish()

        }, 2000) // Delay 2 detik
    }
}
