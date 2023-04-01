package com.example.pokeapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private val ivPicture : ImageView get() = findViewById(R.id.ivPicture);
    private lateinit var myList: MutableList<String>
    private lateinit var rvPokemon: RecyclerView
    var imageURL = ""
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemon = findViewById(R.id.rvPokemon)
        myList = mutableListOf()

        for (i in 1 until 10) {
            Log.d("pokemon", "counting: $i")
            getImageURL()
        }


    }

    private fun getImageURL() {
        val client = AsyncHttpClient()
        counter++
        client["https://pokeapi.co/api/v2/pokemon/$counter", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("pokemon", "response successful$json")
                imageURL = json.jsonObject.getJSONObject("sprites").getString("front_default");
                Log.d("pokemon", "image URL set")

                myList.add(imageURL)

                Log.d("pokemon", "getting item")
                val adapter = ItemAdapter(myList)
                rvPokemon.adapter = adapter
                rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("pokemon", "completed")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("pokemon Error", errorResponse)
            }
        }]
    }
}