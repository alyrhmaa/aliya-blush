package com.example.aliya_blush.Home.pertemuan_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnBack = findViewById<Button>(R.id.btnBack)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // BACK
        btnBack.setOnClickListener {

            finish()
        }

        // HITUNG SEGITIGA
        btnSegitiga.setOnClickListener {

            if (etAlas.text.isEmpty() || etTinggi.text.isEmpty()) {

                Toast.makeText(
                    this,
                    "Isi alas dan tinggi!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val alas =
                    etAlas.text.toString().toDouble()

                val tinggi =
                    etTinggi.text.toString().toDouble()

                val hasil =
                    hitungLuasSegitiga(alas, tinggi)

                tvHasil.text =
                    "Luas Segitiga = $hasil"
            }
        }

        // HITUNG KUBUS
        btnKubus.setOnClickListener {

            if (etSisi.text.isEmpty()) {

                Toast.makeText(
                    this,
                    "Isi sisi dulu!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val sisi =
                    etSisi.text.toString().toDouble()

                val hasil =
                    hitungVolumeKubus(sisi)

                tvHasil.text =
                    "Volume Kubus = $hasil"
            }
        }
    }

    // FUNCTION SEGITIGA
    private fun hitungLuasSegitiga(
        alas: Double,
        tinggi: Double
    ): Double {

        return 0.5 * alas * tinggi
    }

    // FUNCTION KUBUS
    private fun hitungVolumeKubus(
        sisi: Double
    ): Double {

        return sisi * sisi * sisi
    }
}