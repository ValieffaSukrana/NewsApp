package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        adapter = NewsAdapter(emptyList())
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newsRecyclerView.adapter = adapter

        fetchNews()
    }

    private fun fetchNews() {
        val apiKey =
            "491c645d5969471e9271a11785a3712e"

        lifecycleScope.launch {
            try {
                val response: Response<NewsResponse> =
                    RetrofitInstance.api.getTopHeadlines(apiKey = apiKey)
                if (response.isSuccessful && response.body() != null) {
                    val articles = response.body()!!.articles
                    adapter.updateList(articles)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Mayis: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("MainActivity", "Mayis: ${response.code()}")
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Xəta: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("MainActivity", "Xəta: ${e.message}", e)
            }
        }
    }
}
