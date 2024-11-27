package com.example.weatherviewapi.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.weatherviewapi.Domains.Data
import com.example.weatherviewapi.R

class DataAdapter(private val dataList : ArrayList<Data>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val temperature = itemView.findViewById<TextView>(R.id.temperature)
        val humidity = itemView.findViewById<TextView>(R.id.humidity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false))
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

        holder.temperature.text = data.temperature.toString()
        holder.humidity.text = data.humidity.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}