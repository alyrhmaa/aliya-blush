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

import com.example.aliya_blush.Profile.ProfileFragment
import com.example.aliya_blush.Note.FragmentNote
import com.example.aliya_blush.Usulan.UsulanFragment
import com.example.aliya_blush.databinding.ActivityBaseBinding
import com.example.aliya_blush.onboarding.OnBoardingActivity

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. CEK ONBOARDING (Gunakan kunci yang sudah diselaraskan: onboarding_pref)
        val onboardingSelesai = getSharedPreferences(
            "onboarding_pref",
            MODE_PRIVATE
        ).getBoolean("is_onboarding_done", false)

        if (!onboardingSelesai) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
            return
        }

        // 2. CEK LOGIN
        val isLogin = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        ).getBoolean("isLogin", false)

        if (!isLogin) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Fragment pertama
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

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

                R.id.note -> {
                    replaceFragment(FragmentNote())
                    true
                }

                R.id.usulan -> {
                    replaceFragment(UsulanFragment())
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
