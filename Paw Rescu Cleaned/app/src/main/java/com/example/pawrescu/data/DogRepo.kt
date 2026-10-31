#!/usr/bin/env kotlin

package com.example.pawrescu.data

import com.example.pawrescu.R

object DogRepo {
    val sampleDogs = listOf(
        Dog(
            id = 1,
            name = "Buster",
            breed = "Border Collie",
            ageInYears = 2F,
            gender = true,
            size = "Medium",
            location = "Dublin Rehoming Centre",
            description = "Buster is an energetic and intelligent Collie who loves playing fetch and active training sessions.",
            suitableHome = "Needs an active family with a enclosed garden. Best as the only pet.",
            imageResId = R.drawable.dog_buster
        ),
        Dog(
            id = 2,
            name = "Bella",
            breed = "Golden Retriever",
            ageInYears = 3F,
            gender = false,
            size = "Large",
            location = "Cork Rehoming Centre",
            description = "Bella is a gentle, affectionate retriever who loves swimming, long walks, and cuddling on the sofa.",
            suitableHome = "Great with children and other dogs. Ideal for a family looking for a loving companion.",
            imageResId = R.drawable.dog_bella
        ),
        Dog(
            id = 3,
            name = "Milo",
            breed = "Jack Russell Terrier",
            ageInYears = 5F,
            gender = true,
            size = "Small",
            location = "Galway Rehoming Centre",
            description = "Milo is a feisty, fun-loving terrier with a big personality. He loves exploring trail paths and playing with squeaky toys.",
            suitableHome = "Best with an experienced owner who enjoys outdoor activities. Prefers a home without cats.",
            imageResId = R.drawable.dog_milo
        ),
        Dog(
            id = 4,
            name = "Luna",
            breed = "German Shepherd",
            ageInYears = 1F,
            gender = false,
            size = "Large",
            location = "Limerick Rehoming Centre",
            description = "Luna is a smart, loyal young shepherd eager to learn. She thrives on mental stimulation and structured training.",
            suitableHome = "Requires an active owner dedicated to ongoing training and socialization. Secure garden required.",
            imageResId = R.drawable.dog_luna
        ),
        Dog(
            id = 5,
            name = "Charlie",
            breed = "Cavalier King Charles Spaniel",
            ageInYears = 6F,
            gender = true,
            size = "Small",
            location = "Waterford Rehoming Centre",
            description = "Charlie is a calm, sweet-natured lap dog who enjoys relaxed neighborhood strolls and curling up for naps.",
            suitableHome = "Perfect for retirees or anyone working from home. Excellent with kids and gentle pets.",
            imageResId = R.drawable.dog_charlie
        ),
        Dog(
            id = 6,
            name = "Daisy",
            breed = "Lurcher",
            ageInYears = 4F,
            gender = false,
            size = "Large",
            location = "Dublin Rehoming Centre",
            description = "Daisy is a quiet, friendly sighthound who enjoys brief daily zooms followed by hours of comfortable sleeping.",
            suitableHome = "Needs a fenced garden for safe off-lead runs. Friendly with other dogs, but no small furries.",
            imageResId = R.drawable.dog_daisy
        ),
        Dog(
            id = 7,
            name = "Teddy",
            breed = "Cockapoo",
            ageInYears = 0.66F,
            gender = true,
            size = "Medium",
            location = "Kildare Rehoming Centre",
            description = "Teddy is a playful, bouncy pup full of happiness. He loves meeting new people and learning fun puppy tricks.",
            suitableHome = "Ideal for a busy household where someone is around during the day to continue puppy training.",
            imageResId = R.drawable.dog_teddy
        ),
        Dog(
            id = 8,
            name = "Rosie",
            breed = "Staffordshire Bull Terrier",
            ageInYears = 4F,
            gender = false,
            size = "Medium",
            location = "Sligo Rehoming Centre",
            description = "Rosie is a smiley, affectionate dog who adores human companionship, belly rubs, and playing tug-of-war.",
            suitableHome = "Thrives in a loving home where she can be part of family daily life. Suitable for families with older kids.",
            imageResId = R.drawable.dog_rosie
        )
    )

    fun formatAge(ageInYears: Float): String {
        return if (ageInYears < 1f) {
            val months = (ageInYears * 12).toInt().coerceAtLeast(1)
            "$months mo"
        } else {
            val yrs = ageInYears.toInt()
            "$yrs yrs"
        }
    }
}