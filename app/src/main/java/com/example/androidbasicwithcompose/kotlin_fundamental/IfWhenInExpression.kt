package com.example.androidbasicwithcompose.kotlin_fundamental

fun main() {
    ifInExpression("Red")
    println()
    whenInExpression("Black")
}


fun ifInExpression(
    inputTrafficLightColor: String
) {
    val messageResult =
        if (inputTrafficLightColor == "Red") "Stop"
        else if (inputTrafficLightColor == "Yellow") "Slow"
        else if (inputTrafficLightColor == "Green") "Go"
        else  "Invalid traffic-ligth color"

    println(messageResult)
}

fun whenInExpression(inputTrafficLightColor: String) {
    val messageResult =
        when (inputTrafficLightColor) {
            "Red" -> "Stop"
            "Yellow" -> "Slow"
            "Green" -> "Go"
            else -> "Invalid traffic-ligth color"
        }

    println(messageResult)
}