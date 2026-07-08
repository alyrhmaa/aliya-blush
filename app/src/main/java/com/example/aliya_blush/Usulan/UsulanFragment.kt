package com.example.aliya_blush.Usulan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aliya_blush.Data.AppDatabase
import com.example.aliya_blush.Data.Entity.UsulanEntity
import com.example.aliya_blush.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class UsulanFragment : Fragment(R.layout.fragment_usulan) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsulanAdapter
    private lateinit var db: AppDatabase

    private val data = mutableListOf<UsulanEntity>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        recyclerView = view.findViewById(R.id.rvUsulan)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inisialisasi adapter dengan callback untuk menghapus
        adapter = UsulanAdapter(data) { usulan ->
            showDeleteDialog(usulan)
        }

        recyclerView.adapter = adapter

        view.findViewById<View>(R.id.fabAddUsulan)?.setOnClickListener {
            startActivity(
                Intent(requireContext(), UsulanFormActivity::class.java)
            )
        }

        loadData()
    }

    private fun showDeleteDialog(usulan: UsulanEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Usulan")
            .setMessage("Apakah Anda yakin ingin menghapus usulan ini?")
            .setPositiveButton("Hapus") { _, _ ->
                deleteUsulan(usulan)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteUsulan(usulan: UsulanEntity) {
        lifecycleScope.launch {
            try {
                db.usulanDao().delete(usulan)
                Toast.makeText(requireContext(), "Usulan berhasil dihapus", Toast.LENGTH_SHORT).show()
                loadData()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal menghapus usulan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            val result = db.usulanDao().getAll()
            data.clear()
            data.addAll(result)
            adapter.notifyDataSetChanged()
        }
    }
}
