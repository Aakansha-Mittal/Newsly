package com.example.news_api_application

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity, val newsList : List<Article> ) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun itemClick(position: Int) {

        }
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }


    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view, myListener)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.headingTitle.text = currentItem.title

        Picasso.get().load(currentItem.urlToImage).into(holder.headingImg)

    }

    class MyViewHolder(val itemView: View, listener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val headingImg = itemView.findViewById<ImageView>(R.id.heading_img)
        val headingTitle = itemView.findViewById<TextView>(R.id.headingTittle)


        init {
            itemView.setOnClickListener {
                listener.itemClick(adapterPosition)
            }
        }
    }

}