package com.example.aliya_blush.pertemuan_2

import com.example.aliya_blush.R
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // SEGITIGA
        btnSegitiga.setOnClickListener {
            if (etAlas.text.isEmpty() || etTinggi.text.isEmpty()) {
                Toast.makeText(this, "Isi alas & tinggi!", Toast.LENGTH_SHORT).show()
            } else {
                val alas = etAlas.text.toString().toDouble()
                val tinggi = etTinggi.text.toString().toDouble()

                val hasil = hitungLuasSegitiga(alas, tinggi)

                tvHasil.text = "Luas Segitiga = $hasil"
            }
        }

        // KUBUS
        btnKubus.setOnClickListener {
            if (etSisi.text.isEmpty()) {
                Toast.makeText(this, "Isi sisi dulu!", Toast.LENGTH_SHORT).show()
            } else {
                val sisi = etSisi.text.toString().toDouble()

                val hasil = hitungVolumeKubus(sisi)

                tvHasil.text = "Volume Kubus = $hasil"
            }
        }
    }
}