package ca.qc.cstj.tp01.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels

private val viewModel : MainViewModel by viewModels()
private lateinit var binding : ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}