package com.example.aliya_blush.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aliya_blush.databinding.FragmentTabBBinding

class TabBFragment : Fragment() {

    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Data Program Kerja (List)
        val dataProgram = listOf(
            ProductModel("Digitalisasi UMKM", "Program Utama", "https://cdn-icons-png.flaticon.com/512/1554/1554361.png"),
            ProductModel("Literasi Digital", "Edukasi", "https://cdn-icons-png.flaticon.com/512/2912/2912761.png"),
            ProductModel("Website Profil Desa", "Informasi", "https://cdn-icons-png.flaticon.com/512/1067/1067256.png"),
            ProductModel("Sosialisasi Keamanan", "Keamanan", "https://cdn-icons-png.flaticon.com/512/3652/3652191.png")
        )

        val adapter = ProductAdapter(dataProgram) { item ->
            Toast.makeText(requireContext(), "Program: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvTabB.apply {
            // Tampilan LIST (LinearLayoutManager)
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
