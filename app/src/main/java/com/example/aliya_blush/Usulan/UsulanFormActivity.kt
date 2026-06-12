package com.example.aliya_blush.Usulan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aliya_blush.Data.AppDatabase
import com.example.aliya_blush.Data.Entity.UsulanEntity
import com.example.aliya_blush.databinding.ActivityUsulanFormBinding
import kotlinx.coroutines.launch

class UsulanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsulanFormBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsulanFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSimpan.setOnClickListener {

            val judul = binding.etJudul.text.toString()

            val deskripsi = binding.etDeskripsi.text.toString()

            if (judul.isBlank() || deskripsi.isBlank()) {

                Toast.makeText(
                    this,
                    "Lengkapi semua data",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            lifecycleScope.launch {

                db.usulanDao().insert(
                    UsulanEntity(
                        judul = judul,
                        deskripsi = deskripsi
                    )
                )

                finish()
            }
        }
    }
}