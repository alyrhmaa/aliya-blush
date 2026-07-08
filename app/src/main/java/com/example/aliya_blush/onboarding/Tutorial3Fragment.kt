package com.example.aliya_blush.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.R

class Tutorial3Fragment :
    Fragment(R.layout.fragment_tutorial3) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnMulai)
            .setOnClickListener {

                // Simpan status onboarding selesai dengan key yang konsisten
                requireContext()
                    .getSharedPreferences(
                        "onboarding_pref",
                        Context.MODE_PRIVATE
                    )
                    .edit()
                    .putBoolean("is_onboarding_done", true)
                    .apply()

                startActivity(
                    Intent(
                        requireContext(),
                        AuthActivity::class.java
                    )
                )

                requireActivity().finish()
            }
    }
}