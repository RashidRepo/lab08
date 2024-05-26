package com.example.lab08

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab08.databinding.ActivityMainBinding
import com.example.lab08.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScheduleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nameText.text = intent.getStringExtra("name")
        binding.phoneText.text = intent.getStringExtra("phone")
        binding.sizeText.text = intent.getStringExtra("size")
        binding.dateText.text = intent.getStringExtra("date")
        binding.timeText.text = intent.getStringExtra("time")

//        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
//            binding.ratingText.text = "Rating: $rating"
//        }

        binding.sendBtn.setOnClickListener {
            binding.ratingText.text = binding.ratingBar.rating.toString()
        }



//        enableEdgeToEdge()
//        setContentView(R.layout.activity_schedule)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}