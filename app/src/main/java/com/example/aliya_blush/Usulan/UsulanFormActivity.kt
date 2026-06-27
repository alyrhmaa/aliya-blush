package com.example.aliya_blush.Usulan

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aliya_blush.BaseActivity
import com.example.aliya_blush.Data.AppDatabase
import com.example.aliya_blush.Data.Entity.UsulanEntity
import com.example.aliya_blush.databinding.ActivityUsulanFormBinding
import com.example.aliya_blush.utils.PermissionHelper
import com.example.aliya_blush.utils.ReminderHelper
import kotlinx.coroutines.launch

class UsulanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsulanFormBinding
    private lateinit var db: AppDatabase

    // Launcher untuk request permission notifikasi (Android 13+)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Izin notifikasi diberikan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Izin notifikasi ditolak. Pengingat mungkin tidak muncul.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsulanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Cek izin notifikasi saat activity dibuka
        checkNotificationPermission()

        binding.btnSimpan.setOnClickListener {
            saveUsulan()
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!PermissionHelper.hasPermission(this, Manifest.permission.POST_NOTIFICATIONS)) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun saveUsulan() {
        val judul = binding.etJudul.text.toString().trim()
        val deskripsi = binding.etDeskripsi.text.toString().trim()
        val menitStr = binding.etMenit.text.toString().trim()

        if (judul.isEmpty() || deskripsi.isEmpty() || menitStr.isEmpty()) {
            Toast.makeText(this, "Lengkapi semua data", Toast.LENGTH_SHORT).show()
            return
        }

        val menit = menitStr.toIntOrNull() ?: 1

        lifecycleScope.launch {
            try {
                // Simpan ke Database Room
                db.usulanDao().insert(
                    UsulanEntity(
                        judul = judul,
                        deskripsi = deskripsi
                    )
                )

                // Set Pengingat (Reminder)
                ReminderHelper.setReminder(
                    context = this@UsulanFormActivity,
                    minutes = menit,
                    title = "Bina Desa: $judul",
                    message = "Waktunya mengecek perkembangan usulan Anda!",
                    targetActivity = BaseActivity::class.java // Diarahkan ke Home
                )

                Toast.makeText(
                    this@UsulanFormActivity,
                    "Usulan disimpan! Pengingat diset dalam $menit menit.",
                    Toast.LENGTH_LONG
                ).show()

                finish()
            } catch (e: Exception) {
                Toast.makeText(this@UsulanFormActivity, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
