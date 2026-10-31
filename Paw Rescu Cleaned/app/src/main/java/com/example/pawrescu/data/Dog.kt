package com.example.pawrescu.data

class Dog(
    val id: Int, // random int between 1 and 9999999999 (10 digits)
    val name: String,
    val breed: String,
    val ageInYears: Float,
    val gender: Boolean, // true: Male, false: Female
    val size: String, // Small, Medium, Large
    val location: String,
    val description: String,
    val suitableHome: String,
    val imageResId: Int,
    val isFavourite: Boolean = false
)
