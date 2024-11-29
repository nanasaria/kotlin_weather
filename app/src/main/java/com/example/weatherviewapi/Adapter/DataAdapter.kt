package com.example.weatherviewapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherviewapi.Domains.ResponseData
import com.example.weatherviewapi.R
import com.example.weatherviewapi.databinding.ItemDataBinding

class DataAdapter(
    private val dataList: MutableList<ResponseData>,
    private val context:Context
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : ViewHolder = ViewHolder(
        ItemDataBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    // Método para atualizar a lista de dados dinamicamente
    fun updateData(newDataList: List<ResponseData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val itemData: ItemDataBinding
    ) : RecyclerView.ViewHolder(itemData.root) {

        fun bind(data: ResponseData) {
            itemData.humidity.text = data.humidity.toString()
            itemData.temperature.text = data.temperature.toString()
        }

    }
}
