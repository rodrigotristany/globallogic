package com.rodrigotristany.globallogic.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigotristany.globallogic.R
import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.internal.extensions.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_laptops_list.view.*

class LaptopsListAdapter(private val laptops: List<Laptop>, val listener: (Laptop) -> Unit):
        RecyclerView.Adapter<LaptopsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = parent.inflate(R.layout.item_laptops_list)
        return ViewHolder(view)
    }

    override fun getItemCount() = laptops.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(laptops[position])
        holder.itemView.setOnClickListener { listener(laptops[position]) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val laptopName = itemView.laptop_name
        private val laptopImage = itemView.laptop_image

        fun bind(laptop: Laptop) {
            laptopName.text = laptop.title
            Picasso.get()
                .load(laptop.image)
                .placeholder(R.drawable.ic_laptop_mac_black_48dp)
                .error(R.drawable.ic_error_outline_black_48dp)
                .into(laptopImage)
        }
    }
}
