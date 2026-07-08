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
            
            // Menggunakan file preference "onboarding_pref"
            val onboardingPref = getSharedPreferences("onboarding_pref", Context.MODE_PRIVATE)
            val isDone = onboardingPref.getBoolean("is_onboarding_done", false)

            if (!isDone) {
                // Jika belum selesai onboarding, arahkan ke Onboarding
                startActivity(Intent(this, OnBoardingActivity::class.java))
            } else {
                // Jika sudah, cek status login
                val loginPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val isLogin = loginPref.getBoolean("isLogin", false)

                if (isLogin) {
                    startActivity(Intent(this, BaseActivity::class.java))
                } else {
                    startActivity(Intent(this, AuthActivity::class.java))
                }
            }
            
            finish()

        }, 3000)
    }
}
