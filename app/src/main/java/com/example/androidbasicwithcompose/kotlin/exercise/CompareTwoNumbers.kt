package com.example.androidbasicwithcompose.kotlin.exercise

fun main() {
    println(compareTwoNumbers(300, 250))
    println(compareTwoNumbers(300, 300))
    println(compareTwoNumbers(200, 220))
}


fun compareTwoNumbers(totalMinCurrentDay: Int, totalMinYesterday: Int) : Boolean {
    return totalMinCurrentDay > totalMinYesterday
}