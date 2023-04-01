package com.example.pokeapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(myList: List<String>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPicture: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            ivPicture = view.findViewById(R.id.ivPicture)
        }
    }
    val myList = myList;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return myList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(myList[position])
            .centerCrop()
            .into(holder.ivPicture)
    }

}

