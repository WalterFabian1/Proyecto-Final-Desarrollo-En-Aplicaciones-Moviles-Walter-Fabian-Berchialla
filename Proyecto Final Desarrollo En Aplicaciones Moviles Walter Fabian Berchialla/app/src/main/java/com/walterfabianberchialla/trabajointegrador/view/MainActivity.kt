package com.gustavosds.trabajointegrador.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gustavosds.trabajointegrador.R
import com.gustavosds.trabajointegrador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.resultText.observe(this) {
            println("Recibimos un nuevo valor de textResult. $it")
            binding.result.text = it.result
        }

        binding.buttonCompare.setOnClickListener {
            mainViewModel.compareTexts(binding.text1.text.toString(), binding.text2.text.toString())
        }
    }
}