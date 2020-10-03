package com.example.fruits.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruits.requests.entity.Fruit
import com.example.fruits.R
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter (private val items: MutableList<Fruit>) : RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: Fruit = items[position]
        holder.tfvname.text = item.tfvname
        Glide.with(holder.itemView.context)
            .load(item.imageurl)
            .into(holder.imageurl)
//        holder.description.text = item.description
    }

    fun addItem(item: Fruit){
        items.add(item)
        notifyItemInserted(itemCount)
    }
}
class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val tfvname: TextView = itemView.findViewById(R.id.tvFruitNameValue)
    val imageurl: ImageView = itemView.findViewById(R.id.imgFruit)
    val card: CardView = itemView.findViewById(R.id.card)
//    val description: TextView = itemView.findViewById(R.id.tvDescription)
}