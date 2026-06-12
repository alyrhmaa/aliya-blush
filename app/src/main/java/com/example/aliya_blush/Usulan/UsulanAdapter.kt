package com.example.aliya_blush.Usulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aliya_blush.Data.Entity.UsulanEntity
import com.example.aliya_blush.R

class UsulanAdapter(
    private val list: List<UsulanEntity>
) : RecyclerView.Adapter<UsulanAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvDeskripsi: TextView = view.findViewById(R.id.tvDeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usulan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvJudul.text = item.judul
        holder.tvDeskripsi.text = item.deskripsi
    }

    override fun getItemCount(): Int = list.size
}