package com.example.countriesapp

data class Country (
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val languages: Any,
    val timezones: List<String>,
    val flags: Flag
)

data class Name (val common: String)
data class Flag(val png: String)