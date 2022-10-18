package com.example.androidbasicwithcompose.kotlin_fundamental

fun main() {
    val trafficLight = "Red"

    when(trafficLight) {
        "Red" -> println("Stop")
        "Yellow" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Don't traffic light color")
    }
}