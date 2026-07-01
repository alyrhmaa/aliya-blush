package com.example.aliya_blush.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.aliya_blush.R
import com.example.aliya_blush.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Contoh Implementasi ArrayAdapter (simple_list_item_1)
        // val itemsArray = arrayOf("Item 1", "Item 2", "Item 3")
        // val adapterArray = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsArray)

        // 2. Contoh Implementasi SimpleAdapter (simple_list_item_2)
        /*
        val dataSimple = mutableListOf<Map<String, String>>()
        val map1 = mapOf("title" to "Title 1", "sub" to "Subtitle 1")
        dataSimple.add(map1)
        val adapterSimple = SimpleAdapter(
            requireContext(), dataSimple, android.R.layout.simple_list_item_2,
            arrayOf("title", "sub"), intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        */

        // 3. Implementasi Custom Adapter (Gambar + Teks menggunakan Glide)
        val listServices = listOf(
            Service("Layanan Administrasi", "https://cdn-icons-png.flaticon.com/512/1067/1067256.png"),
            Service("Informasi Kesehatan", "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"),
            Service("Pusat Pengaduan", "https://cdn-icons-png.flaticon.com/512/1554/1554361.png"),
            Service("Agenda Kegiatan", "https://cdn-icons-png.flaticon.com/512/3652/3652191.png")
        )

        val customAdapter = object : ArrayAdapter<Service>(requireContext(), R.layout.item_service, listServices) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_service, parent, false)
                val currentService = getItem(position)

                val textView = itemView.findViewById<TextView>(R.id.tvService)
                val imageView = itemView.findViewById<ImageView>(R.id.ivService)

                textView.text = currentService?.name
                
                // Menggunakan Glide untuk memuat gambar
                Glide.with(context)
                    .load(currentService?.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView)

                return itemView
            }
        }

        // Set adapter ke ListView
        binding.listAbout.adapter = customAdapter

        // 4. Menangani klik item di ListView
        binding.listAbout.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val clickedService = listServices[position]
            Toast.makeText(requireContext(), "Membuka: ${clickedService.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
