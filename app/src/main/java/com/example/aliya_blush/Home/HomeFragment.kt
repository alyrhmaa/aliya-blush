package com.example.aliya_blush.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.Home.pertemuan_2.Kalkulator_Bangun
import com.example.aliya_blush.Home.pertemuan_4.Custom1_Activity
import com.example.aliya_blush.Home.pertemuan_4.Custom2_Activity
import com.example.aliya_blush.Home.pertemuan_6.WebView_Activity
import com.example.aliya_blush.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Menghubungkan Fragment dengan XML
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Menampilkan layout Fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TOOLBAR
        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Home"
            }

        val sharedPref =
            requireContext().getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )
        binding.btnToKalkulator.setOnClickListener {

            val intent = Intent(
                requireContext(),
                Kalkulator_Bangun::class.java
            )

            startActivity(intent)
        }
        binding.btnToCustom1.setOnClickListener {

            val intent = Intent(
                requireContext(),
                Custom1_Activity::class.java
            )

            startActivity(intent)
        }
        binding.btnToCustom2.setOnClickListener {

            val intent = Intent(
                requireContext(),
                Custom2_Activity::class.java
            )

            startActivity(intent)
        }
        binding.btnToWeb.setOnClickListener {

            val intent = Intent(
                requireContext(),
                WebView_Activity::class.java
            )

            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->

                    // Reset Status Login
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", false)
                    editor.apply()

                    dialog.dismiss()

                    // Kembali ke AuthActivity
                    val intent = Intent(
                        requireContext(),
                        AuthActivity::class.java
                    )

                    startActivity(intent)
                    requireActivity().finish()
                }

                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }

                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Membersihkan Binding
        _binding = null
    }
}