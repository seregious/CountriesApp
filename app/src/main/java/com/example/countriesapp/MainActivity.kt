package com.example.countriesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            lifecycleScope.launch {
                val countryName = binding.countryNameText.text.toString()

                val countries = restCountriesApi.getCountryByName(countryName)
                val country = countries[0]
                setText(country)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setText(country: Country) = with(binding){
        countryName.text = country.name
        countryDescription.text =
            """
                Capital: ${country.capital}
                Population: ${country.population}
                Languages: ${country.languages.toString()}
                Area: ${country.area}
            """.trimIndent()
    }
}