package com.example.aliya_blush.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.BaseActivity
import com.example.aliya_blush.R
import com.example.aliya_blush.Home.pertemuan_3.Login_Activity

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val sp = getSharedPreferences("LOGIN", MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({

            if (sp.getBoolean("isLogin", false)) {

                startActivity(
                    Intent(this, BaseActivity::class.java)
                )

            } else {

                startActivity(
                    Intent(this, Login_Activity::class.java)
                )
            }

            finish()

        }, 2000)
    }
}