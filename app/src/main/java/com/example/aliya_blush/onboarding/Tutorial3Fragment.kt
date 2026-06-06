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

                requireContext()
                    .getSharedPreferences(
                        "onboarding",
                        Context.MODE_PRIVATE
                    )
                    .edit()
                    .putBoolean("selesai", true)
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