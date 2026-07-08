package com.example.aliya_blush.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aliya_blush.AuthActivity
import com.example.aliya_blush.Data.Api.PostApiClient
import com.example.aliya_blush.Home.berita.PhotoAdapter
import com.example.aliya_blush.Home.berita.PostAdapter
import com.example.aliya_blush.Home.pertemuan_10.TenthActivity
import com.example.aliya_blush.Home.pertemuan_11.CameraFeatureActivity
import com.example.aliya_blush.Home.pertemuan_11.QrGeneratorActivity
import com.example.aliya_blush.Home.pertemuan_11.QrScannerActivity
import com.example.aliya_blush.Home.pertemuan_2.MainActivity
import com.example.aliya_blush.Home.pertemuan_4.Custom1_Activity
import com.example.aliya_blush.Home.pertemuan_4.Custom2_Activity
import com.example.aliya_blush.Home.pertemuan_6.WebView_Activity
import com.example.aliya_blush.R
import com.example.aliya_blush.Usulan.UsulanFormActivity
import com.example.aliya_blush.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        // RecyclerView Berita
        binding.rvBerita.layoutManager =
            LinearLayoutManager(requireContext())

        loadBerita()

        // RecyclerView Gallery
        loadPhoto()

        // Menu Usulan Warga
        binding.cardLayanan.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    UsulanFormActivity::class.java
                )
            )
        }

        // Kalkulator
        binding.btnToKalkulator.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MainActivity::class.java
                )
            )
        }

        // Bina Desa (Tab & ViewPager) - Menggunakan tombol Custom 1
        binding.btnToCustom1.text = "Bina Desa (Tab)"
        binding.btnToCustom1.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    TenthActivity::class.java
                )
            )
        }

        // Custom 2
        binding.btnToCustom2.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    Custom2_Activity::class.java
                )
            )
        }

        // WebView
        binding.btnToWeb.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    WebView_Activity::class.java
                )
            )
        }

        // --- FITUR KAMERA & QR ---
        binding.btnToCamera.setOnClickListener {
            startActivity(Intent(requireContext(), CameraFeatureActivity::class.java))
        }

        binding.btnToGenerator.setOnClickListener {
            startActivity(Intent(requireContext(), QrGeneratorActivity::class.java))
        }

        binding.btnToScanner.setOnClickListener {
            startActivity(Intent(requireContext(), QrScannerActivity::class.java))
        }

        // Logout
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->

                    requireContext()
                        .getSharedPreferences(
                            "user_pref",
                            MODE_PRIVATE
                        )
                        .edit()
                        .clear()
                        .apply()

                    dialog.dismiss()

                    startActivity(
                        Intent(
                            requireContext(),
                            AuthActivity::class.java
                        )
                    )

                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadBerita() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response =
                    PostApiClient.apiService.getPosts()

                binding.rvBerita.adapter =
                    PostAdapter(response.posts)

            } catch (e: Exception) {

                e.printStackTrace()

                Toast.makeText(
                    requireContext(),
                    "Gagal memuat berita",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadPhoto() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response =
                    PostApiClient.apiService.getPosts()

                val beritaDesaDenganGambar =
                    response.posts.map { post ->

                        post.copy(
                            image = "https://loremflickr.com/400/300/village,people?lock=${post.id}"
                        )
                    }

                binding.rvGallery.layoutManager =
                    GridLayoutManager(requireContext(), 2)

                binding.rvGallery.adapter =
                    PhotoAdapter(beritaDesaDenganGambar)

            } catch (e: Exception) {

                e.printStackTrace()

                Toast.makeText(
                    requireContext(),
                    "Gagal memuat galeri",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
