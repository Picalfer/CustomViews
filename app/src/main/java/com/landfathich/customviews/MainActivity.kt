package com.landfathich.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.landfathich.customviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding.bottomButtons) {
            negativeButton.text = getString(R.string.cancel)
            positiveButton.text = getString(R.string.ok)

            positiveButton.setOnClickListener {
                progress.visibility = View.VISIBLE
                negativeButton.visibility = View.INVISIBLE
                positiveButton.visibility = View.INVISIBLE
            }
        }
    }
}