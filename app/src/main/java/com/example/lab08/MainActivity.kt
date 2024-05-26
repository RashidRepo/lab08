package com.example.lab08

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab08.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val pizzaSize = arrayListOf("Please Select", "Small", "Medium", "Large", "Extra-Large")

        binding.seekBar.max = pizzaSize.size - 1 // Ensure the SeekBar max matches the array size

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Debugging: Ensure the progress is within bounds
                if (progress < 0 || progress >= pizzaSize.size) {
                    println("Progress out of bounds: $progress")
                } else {
                    binding.seekText.text = pizzaSize[progress]
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optionally handle start of touch event
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optionally handle stop of touch event
            }
        })


        binding.selectDateBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val day= c.get(Calendar.DAY_OF_MONTH)
            val month =c.get(Calendar.MONTH)
            val year =c.get(Calendar.YEAR)
            val myDatePicker =
                DatePickerDialog(this, R.style.
                ThemeOverlay,DatePickerDialog.OnDateSetListener {DatePicker,
                                                                 Year,Month,Day ->
                    binding.dateText.text="$Day/ ${Month+1} /$Year"},year,month,day)
            myDatePicker.show()
        }

        binding.selectTimeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val hour =c.get(Calendar.HOUR_OF_DAY)
            val minutes =c.get(Calendar.MINUTE)
            val myTimePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                // Create a Calendar object and set the hour and minute
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)

                // Format the selected time to include AM or PM
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val formattedTime = timeFormat.format(selectedTime.time)

                // Set the formatted time to the TextView
                binding.timeText.text = formattedTime
            }, hour, minutes, false)
            myTimePicker.show()
        }

        binding.schedBtn.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            intent.putExtra("name", binding.nameEditText.text.toString())
            intent.putExtra("phone", binding.phoneEditText.text.toString())
            intent.putExtra("size", binding.seekText.text.toString())
            intent.putExtra("date", binding.dateText.text.toString())
            intent.putExtra("time", binding.timeText.text.toString())
            startActivity(intent)
        }

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}