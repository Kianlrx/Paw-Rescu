package com.example.pawrescu.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawrescu.data.DogRepo
import com.example.pawrescu.ui.theme.PawRescuTheme

@Composable
fun SearchResultsScreenContent(
    nameQuery: String = "",
    locationQuery: String = "",
    sizeQuery: String = "",
    genderQuery: String = "",
    breedQuery: String = "",
    ageQuery: String = "",
    onNavigateToDogDetail: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val filteredDogs = remember(nameQuery, locationQuery, sizeQuery, genderQuery, breedQuery, ageQuery) {
        DogRepo.sampleDogs.filter { dog ->
            val matchesName = nameQuery.isBlank() || dog.name.contains(nameQuery, ignoreCase = true)
            val matchesLocation = locationQuery.isBlank() || dog.location.contains(locationQuery, ignoreCase = true)
            val matchesSize = sizeQuery.isBlank() || dog.size.equals(sizeQuery, ignoreCase = true)
            val matchesGender = genderQuery.isBlank() || 
                (genderQuery.equals("male", ignoreCase = true) && dog.gender) ||
                (genderQuery.equals("female", ignoreCase = true) && !dog.gender)
            val matchesBreed = breedQuery.isBlank() || dog.breed.contains(breedQuery, ignoreCase = true)
            val matchesAge = ageQuery.isBlank() || {
                val ageFloat = ageQuery.toFloatOrNull()
                if (ageFloat != null) {
                    dog.ageInYears == ageFloat || dog.ageInYears.toInt() == ageFloat.toInt()
                } else {
                    true
                }
            }()

            matchesName && matchesLocation && matchesSize && matchesGender && matchesBreed && matchesAge
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Search Results (${filteredDogs.size})",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Back to Search",
                modifier = Modifier
                    .clickable(onClick = onBackClick)
                    .padding(4.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        if (filteredDogs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No dogs found matching your search criteria.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 110.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredDogs) { dog ->
                    DogGridItem(
                        dog = dog,
                        onClick = { onNavigateToDogDetail(dog.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
    PawRescuTheme {
        SearchResultsScreenContent(
            onNavigateToDogDetail = {},
            onBackClick = {}
        )
    }
}
