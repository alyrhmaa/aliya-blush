package com.example.aliya_blush.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aliya_blush.Data.AppDatabase
import com.example.aliya_blush.Data.Entity.NoteEntity
import com.example.aliya_blush.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class FragmentNote : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    /** Variabel database & Adapter **/
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        binding.toolbar.title = "Kegiatan Desa"

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        /** Inisialisasi AppDatabase & Adapter **/
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes,this)

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        /** Tambah ini sebagai garis pemisah **/
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        fetchNotes()

        // FAB membuka NoteFormActivity
        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(requireContext(), NoteFormActivity::class.java)
            )
        }
    }

    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll()

            notes.clear()
            notes.addAll(data)

            adapter.notifyDataSetChanged()
        }
    }
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) //Hapus Note
            fetchNotes()              //Fetch lagi data notes terbaru
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}