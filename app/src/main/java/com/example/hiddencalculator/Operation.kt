package com.example.hiddencalculator

class Operation(private val first: Double, private val second: Double) {

    fun sum() = first + second
    fun diff() = first - second
    fun mult() = first * second
    fun div() = first / second
}