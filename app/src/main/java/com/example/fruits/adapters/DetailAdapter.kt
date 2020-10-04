package com.example.fruits.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruits.R
import com.example.fruits.requests.entity.FruitDetail

class DetailAdapter (private val items: MutableList<FruitDetail>, context: Context) : RecyclerView.Adapter<MyViewHolderDetail>(){
    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDetail {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return MyViewHolderDetail(viewHolder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolderDetail, position: Int) {
        val item: FruitDetail = items[position]
        holder.tfvname.text = item.tfvname
        Glide.with(holder.itemView.context)
            .load(item.imageurl)
            .into(holder.imageurl)
        holder.botname.text = item.botname
        holder.othname.text = item.othname
        holder.description.text = item.description
    }

    fun addItem(item: FruitDetail){
        items.add(item)
        notifyItemInserted(itemCount)
    }
}
class MyViewHolderDetail (itemView: View): RecyclerView.ViewHolder(itemView){
    val tfvname: TextView = itemView.findViewById(R.id.name_fruit)
    val imageurl: ImageView = itemView.findViewById(R.id.fruit_detail_img)
    val botname: TextView = itemView.findViewById(R.id.botname_detail)
    val othname: TextView = itemView.findViewById(R.id.othname_detail)
    val description: TextView = itemView.findViewById(R.id.desc_detail)
}