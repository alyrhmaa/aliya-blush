package com.example.aliya_blush.Home.service

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layananList = listOf(
            ServiceModel("Surat Domisili"),
            ServiceModel("Data Kependudukan"),
            ServiceModel("Agenda Kegiatan Desa"),
            ServiceModel("Laporan Keuangan Desa"),
            ServiceModel("Pengajuan Bantuan UMKM"),
            ServiceModel("Jadwal Posyandu")
        )

        val adapter = ServiceAdapter(this, layananList)

        binding.listService.adapter = adapter
    }
}