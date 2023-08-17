package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcItemBinding

class RcAdapter(private val list: List<String>) : RecyclerView.Adapter<RcAdapter.RcViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolder {
        val binding = RcItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return RcViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RcViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RcViewHolder(var binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.title.text = title
        }
    }

}