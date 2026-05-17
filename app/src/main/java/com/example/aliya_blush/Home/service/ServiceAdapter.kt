package com.example.aliya_blush.Home.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.aliya_blush.databinding.ItemServiceBinding

class ServiceAdapter(
    context: Context,
    private val services: List<ServiceModel>
) : ArrayAdapter<ServiceModel>(context, 0, services) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        val data = services[position]

        binding.tvService.text = data.title

        binding.root.setOnClickListener {

            Toast.makeText(
                context,
                "Memilih ${data.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}