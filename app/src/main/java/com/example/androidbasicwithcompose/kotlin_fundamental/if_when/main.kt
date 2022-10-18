package com.example.androidbasicwithcompose.kotlin_fundamental

fun main() {
    val trafficLightColor = "Black"

    if (trafficLightColor == "Red") {
        println("Stop")
    } else if (trafficLightColor == "Yellow") {
        println("Slow")
    } else if (trafficLightColor == "Green") {
        println("Go")
    } else {
        println("Invalid - traffic-light Color")
    }

    val x = 7
    when (x) {
        1, 2, 3, 4, 5 -> println("Numbers between 1 to 5")
        in 6..10 -> println("Number between 6 to 10")
        else -> println("x isn't a prime number between 1 and 10.")
    }

    val y: Any = 15

    when(y) {
        1, 2, 3, 4, 5 -> println("Numbers between 1 to 5")
        in 6..10 -> println("Number between 6 to 10")
        is Int -> println("$y is type Int")
        else -> println("x isn't a prime number between 1 and 10.")
    }

}