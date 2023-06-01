package com.landfathich.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.landfathich.customviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.bottomButtons.setListener {
            if (it == BottomButtonAction.POSITIVE) {
                Toast.makeText(this, "Positive button pressed", Toast.LENGTH_SHORT).show()
            } else if (it == BottomButtonAction.NEGATIVE) {
                Toast.makeText(this, "Negative button pressed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}