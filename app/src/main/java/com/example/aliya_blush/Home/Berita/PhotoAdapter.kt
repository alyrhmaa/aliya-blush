package com.example.aliya_blush.Home.berita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aliya_blush.Data.Model.PostModel
import com.example.aliya_blush.databinding.ItemPhotoBinding

class PhotoAdapter(
    private val items: List<PostModel>
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(
        val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {

        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PhotoViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.binding.apply {

            // 🔥 FIX UTAMA DI SINI
            if (item.image.isNullOrEmpty()) {

                imgPhoto.setImageResource(
                    android.R.drawable.ic_menu_report_image
                )

            } else {

                Glide.with(root)
                    .load(item.image)
                    .into(imgPhoto)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}