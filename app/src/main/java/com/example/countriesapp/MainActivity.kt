package com.example.countriesapp

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val message = "Country does not exist"
            lifecycleScope.launch {
                try {
                    pressSearchButton()
                } catch (e: Exception) {
                    val message = "Country does not exist"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()

                }
            }
        }

    }

    private suspend fun pressSearchButton() {
        val countryName = binding.countryNameText.text.toString()

            val countries = restCountriesApi.getCountryByName(countryName)
            val country = countries[0]
            setText(country)
            getVisible()

    }

    @SuppressLint("SetTextI18n")
    private fun setText(country: Country) = with(binding){
        countryName.text = country.name.common
        countryDescription.text =
            """
                Capital: ${country.capital[0]}
                Population: ${intFormating(country.population)}
                Area: ${intFormating(country.area)}
                Timezones: ${timezonesText(country)}
            """.trimIndent()
        runOnUiThread {
            Picasso.get().load(country.flags.png).into(countryFlag)
        }

    }

    private fun timezonesText(country: Country): String {
        var text = ""
        for (zone in country.timezones) {
            text += zone
        }
        return text
    }

    private fun getLanguage(country: Country): String {
        val text = country.languages.toString()
        val delim = ", "
        var list = text.split(delim).joinToString()
        list = list.filter { it.isLetter() }

        return list
    }

    private fun intFormating(number: Long): String {
        return NumberFormat.getInstance().format(number)
    }

    private fun getVisible() =with(binding) {
        countryName.visibility = View.VISIBLE
        countryDescription.visibility  = View.VISIBLE

    }
}