package com.example.news_api_application

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getNewsArticles("news","3948539186184ac6b1800bf9b6b1daef" )

        retrofitData.enqueue( object : Callback<MyData?> {

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val responseBody = response.body()
                val newsList = responseBody?.articles!!

                myAdapter = MyAdapter(this@MainActivity, newsList)
                recyclerView.adapter = myAdapter

                myAdapter.setOnItemClickListener( object : MyAdapter.onItemClickListener{
                    override fun itemClick(position: Int) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(newsList[position].url)
                        startActivity(intent)
                    }
                })

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }


            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure " + t.message)
            }

        })

    }
}