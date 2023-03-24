package com.example.pokeapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private val nextButton : Button get() = findViewById(R.id.btnNext);
    private val tvName: TextView get() = findViewById(R.id.tvName);
    private val tvType : Button get() = findViewById(R.id.tvType);
    private val ivPicture : Button get() = findViewById(R.id.ivPicture);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton.setOnClickListener{
            getImageURL()
        }

        getImageURL()
    }

    private fun getImageURL() {
        val client = AsyncHttpClient()

        var petImageURL = ""

        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful")
                petImageURL = json.jsonObject.getString("message")
                Log.d("petImageURL", "pet image URL set")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }
}