package com.example.aliya_blush.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aliya_blush.Home.pertemuan_2.MainActivity
import com.example.aliya_blush.Home.pertemuan_4.Custom1_Activity
import com.example.aliya_blush.Home.pertemuan_4.Custom2_Activity
import com.example.aliya_blush.Home.pertemuan_6.WebView_Activity
import com.example.aliya_blush.Home.pertemuan_9.NinthActivity
import com.example.aliya_blush.Home.service.ServiceActivity
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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        val sharedPref = requireContext().getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        binding.cardLayanan.setOnClickListener {
            startActivity(Intent(requireContext(), ServiceActivity::class.java))
        }

        binding.btnToKalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.btnToCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom1_Activity::class.java))
        }

        binding.btnToCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), Custom2_Activity::class.java))
        }

        binding.btnToWeb.setOnClickListener {
            startActivity(Intent(requireContext(), WebView_Activity::class.java))
        }

        // LOGOUT
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->

                    sharedPref.edit().clear().apply()

                    dialog.dismiss()

                    val intent = Intent(requireContext(), NinthActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
