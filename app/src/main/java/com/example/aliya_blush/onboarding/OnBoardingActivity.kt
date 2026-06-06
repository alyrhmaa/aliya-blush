package com.example.aliya_blush.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // JIKA SEBENARNYA SUDAH SELESAI, JANGAN TAMPILKAN LAGI
        val onboardingSelesai = getSharedPreferences("onboarding", MODE_PRIVATE)
            .getBoolean("selesai", false)
        
        if (onboardingSelesai) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = OnBoardingAdapter(this, fragments)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}
