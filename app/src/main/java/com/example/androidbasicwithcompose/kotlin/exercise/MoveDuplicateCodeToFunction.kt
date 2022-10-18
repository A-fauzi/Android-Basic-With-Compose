package com.example.androidbasicwithcompose.kotlin.exercise

fun main() {
    weather("Angkara", 27, 31, 82)
    weather("Tokyo", 32, 36, 10)
    weather("Cape Town", 59, 64, 2)
    weather("Guatemala City", 50, 55, 7)
}

fun weather(city: String, lowTemp: Int, highTemp: Int, chanceOfRain: Int) {

    println("City: $city")
    println("Low temperature: $lowTemp, High temperature: $highTemp")
    println("Chance of rain: $chanceOfRain%")
    println()
}


