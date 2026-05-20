package com.example.aliya_blush.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aliya_blush.databinding.FragmentTabABinding

class TabAFragment : Fragment() {

    private var _binding: FragmentTabABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Data Bina Desa
        val dummyData = listOf(
            ProductModel("Pembangunan Jembatan", "Program Infrastruktur", "https://img.freepik.com/free-photo/bridge-construction-site_1150-17743.jpg"),
            ProductModel("Posyandu Melati", "Program Kesehatan", "https://img.freepik.com/free-vector/pediatrician-concept-illustration_114360-1489.jpg"),
            ProductModel("Pelatihan UMKM", "Pemberdayaan Ekonomi", "https://img.freepik.com/free-photo/people-working-together-medium-shot_23-2149330554.jpg"),
            ProductModel("Pembersihan Selokan", "Program Kebersihan", "https://img.freepik.com/free-vector/volunteers-cleaning-park-concept_23-2148530391.jpg"),
            ProductModel("Perpustakaan Desa", "Program Pendidikan", "https://img.freepik.com/free-photo/library-with-books_1150-14544.jpg"),
            ProductModel("Bantuan Bibit Tani", "Program Pertanian", "https://img.freepik.com/free-photo/farmer-holding-young-plant_1150-8332.jpg")
        )

        val adapter = ProductAdapter(dummyData) { item ->
            Toast.makeText(requireContext(), "Program: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvTabA.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
