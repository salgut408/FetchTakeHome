package com.example.android.fetchtakehomeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fetchtakehomeapp.databinding.SingleItemBinding
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: SingleItemBinding) :
            RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<JsonResponseModel>() {
        override fun areItemsTheSame(
            oldItem: JsonResponseModel,
            newItem: JsonResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: JsonResponseModel,
            newItem: JsonResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {

            itemId.text = item.id.toString()
            itemName.text = item.name
            listId.text = item.listId.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }




}