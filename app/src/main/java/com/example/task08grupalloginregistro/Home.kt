package com.example.task08grupalloginregistro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task08grupalloginregistro.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text= "Bienvenid@ ${intent.getStringExtra("USUARIO")}"
    }
}