package com.example.weatherviewapi.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherviewapi.Adapter.DataAdapter
import com.example.weatherviewapi.Domains.Data
import com.example.weatherviewapi.R

class MainActivity : AppCompatActivity() {
    private lateinit var textDateTime : TextView
    private lateinit var recyclreView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        textDateTime = findViewById(R.id.textView3)
//
//        textDateTime.setText("Ola Date Time")


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclreView = findViewById(R.id.viewHistoric)
        recyclreView.layoutManager = LinearLayoutManager(this)
        val dataList = ArrayList<Data>()
        //chamada da API

        //Construção da lista e converta os types de data e hora com DateFormater:
//        dataList.add(Data("", ""))

        val adapter = DataAdapter(dataList)
        recyclreView.adapter = adapter
    }
}