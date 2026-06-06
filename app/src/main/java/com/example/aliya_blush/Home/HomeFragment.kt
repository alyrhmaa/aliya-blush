package com.example.aliya_blush.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.Data.Api.PostApiClient
import com.example.aliya_blush.Home.berita.PostAdapter
import com.example.aliya_blush.Home.berita.PhotoAdapter
import com.example.aliya_blush.Home.pertemuan_2.MainActivity
import com.example.aliya_blush.Home.pertemuan_4.Custom1_Activity
import com.example.aliya_blush.Home.pertemuan_4.Custom2_Activity
import com.example.aliya_blush.Home.pertemuan_6.WebView_Activity
import com.example.aliya_blush.Home.service.ServiceActivity
import com.example.aliya_blush.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

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

        // =====================
        // RECYCLERVIEW BERITA
        // =====================
        binding.rvBerita.layoutManager = LinearLayoutManager(requireContext())
        loadBerita()

        // =====================
az        // RECYCLERVIEW GALLERY (Disesuaikan ke Berita Desa)
        // =====================
        loadPhoto()

        // =====================
        // BUTTON CLICK LISTENERS
        // =====================
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
                    requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
                        .edit().clear().apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    private fun loadBerita() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = PostApiClient.apiService.getPosts()
                binding.rvBerita.adapter = PostAdapter(response.posts)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Kita gunakan API Posts agar teksnya tentang artikel/berita
                val response = PostApiClient.apiService.getPosts()

                // Kita manipulasi datanya agar setiap berita memiliki gambar bertema "Desa"
                // Menggunakan loremflickr dengan keyword 'village,people' agar sesuai tema aplikasi
                val beritaDesaDenganGambar = response.posts.map { post ->
                    post.copy(image = "https://loremflickr.com/400/300/village,people?lock=${post.id}")
                }

                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvGallery.adapter = PhotoAdapter(beritaDesaDenganGambar)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Gagal memuat gallery", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
