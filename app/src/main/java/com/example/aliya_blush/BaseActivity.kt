package com.example.aliya_blush

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.aliya_blush.About.AboutFragment
import com.example.aliya_blush.Home.HomeFragment
import com.example.aliya_blush.More.ProfileFragment
import com.example.aliya_blush.databinding.ActivityBaseBinding
import com.example.aliya_blush.onboarding.OnBoardingActivity

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. CEK ONBOARDING
        val onboardingSelesai = getSharedPreferences("onboarding", MODE_PRIVATE)
            .getBoolean("selesai", false)

        if (!onboardingSelesai) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
            return
        }

        // 2. CEK LOGIN
        val isLogin = getSharedPreferences("user_pref", MODE_PRIVATE)
            .getBoolean("isLogin", false)

        if (!isLogin) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        // 3. JIKA SUDAH SEMUA, TAMPILKAN HOME
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fragment pertama (Default Home)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Bottom Navigation
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
