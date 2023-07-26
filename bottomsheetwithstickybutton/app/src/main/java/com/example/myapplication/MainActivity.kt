package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val options = listOf<String>(
            "Share with Friends",
            "Bookmark",
            "Add to Favourites",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "More Information",
            "Share with Friends"
        )

        binding.listViewOptions.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            options
        )
    }
}