package com.example.fruits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter(private val items: MutableList<Fruit>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var list: List<Fruit>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let {
            holder.bind(it[position])
        }
    }

    fun updateData(list: List<Fruit>?) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addItem(item: Fruit){
        items.add(item)
        notifyItemInserted(itemCount)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(fruit: Fruit) {
            itemView.tvFruitNameValue.text = "${fruit.botname}"
        }
    }
}