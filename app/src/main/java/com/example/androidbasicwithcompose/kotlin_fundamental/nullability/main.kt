package com.example.androidbasicwithcompose.kotlin_fundamental.nullability

fun main() {
    val favouriteActor: String? = null

    val lengthOfName = favouriteActor?.length ?: 0 // elvis operator, handle null

    println("Total length char in favourite actor $favouriteActor : $lengthOfName")
}