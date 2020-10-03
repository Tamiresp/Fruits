package com.example.fruits.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruits.requests.entity.Fruit
import com.example.fruits.R
import com.example.fruits.activities.FruitDetailActivity
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter (private val items: MutableList<Fruit>, context: Context) : RecyclerView.Adapter<MyViewHolder>(){
    private val context = context

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

        holder.card.setOnClickListener {
            val intent = Intent(context, FruitDetailActivity::class.java)
            intent.putExtra("image", item.imageurl)
            intent.putExtra("name", item.tfvname)
            intent.putExtra("botname", item.botname)
            intent.putExtra("othname", item.othname)
            context.startActivity(intent)
        }
    }

    fun addItem(item: Fruit){
        items.add(item)
        notifyItemInserted(itemCount)
    }
}
class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val tfvname: TextView = itemView.findViewById(R.id.tvFruitNameValue)
    val botname: TextView = itemView.findViewById(R.id.tvFruitNameValue)
    val othname: TextView = itemView.findViewById(R.id.tvFruitNameValue)
    val imageurl: ImageView = itemView.findViewById(R.id.imgFruit)
    val card: CardView = itemView.findViewById(R.id.card)
}