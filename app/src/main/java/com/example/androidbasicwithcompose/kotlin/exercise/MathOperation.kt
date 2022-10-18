package com.example.androidbasicwithcompose.kotlin.exercise


fun main() {
    subtract()
}

fun subtract() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8

    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
}

fun add(value1: Int, value2: Int) : Int {
    return value1 + value2
}