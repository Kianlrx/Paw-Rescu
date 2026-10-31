package com.example.pawrescu.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawrescu.ui.theme.PawRescuTheme

class AdoptionInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawRescuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdoptionInfoScreenContent(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun AdoptionInfoScreenContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header Banner Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Dog Adoption Guide",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Everything you need to know about welcoming a rescued dog into your home and our rehoming process.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        // Section 1
        AdoptionSection(title = "The Adoption Process") {
            AdoptionStepRow(
                step = "Step 1",
                title = "Browse & Search",
                description = "Explore available dogs on our platform and find a companion whose energy, size, and needs match your lifestyle."
            )
            AdoptionStepRow(
                step = "Step 2",
                title = "Submit an Application",
                description = "Fill out our adoption inquiry form with your household details, garden setup, and previous pet experience."
            )
            AdoptionStepRow(
                step = "Step 3",
                title = "Meet & Greet",
                description = "Our rehoming team reviews your application and arranges a meeting at one of our centres to introduce you to the dog."
            )
            AdoptionStepRow(
                step = "Step 4",
                title = "Welcome Home",
                description = "Once approved, finalize adoption paperwork and bring your new furry family member home!"
            )
        }

        // Section 2
        AdoptionSection(title = "What's Included in Adoption") {
            AdoptionFeatureRow(icon = Icons.Default.CheckCircle, text = "Complete veterinary health check & vaccinations")
            AdoptionFeatureRow(icon = Icons.Default.CheckCircle, text = "Neutering / Spaying procedure")
            AdoptionFeatureRow(icon = Icons.Default.CheckCircle, text = "Microchipping and registration")
            AdoptionFeatureRow(icon = Icons.Default.CheckCircle, text = "Flea and worm preventative treatments")
            AdoptionFeatureRow(icon = Icons.Default.CheckCircle, text = "4 weeks of free pet insurance & lifelong behavioral support")
        }

        // Section 3
        AdoptionSection(title = "Settling In: The 3-3-3 Rule") {
            AdoptionRuleRow(time = "3 Days", description = "Decompression: Feeling overwhelmed, unsure, and testing boundaries.")
            AdoptionRuleRow(time = "3 Weeks", description = "Routine & Trust: Settling into daily routines, learning house rules, and building trust.")
            AdoptionRuleRow(time = "3 Months", description = "True Personality: Feeling completely at home, secure, and showing their true loving character.")
        }

        // Section 4
        AdoptionSection(title = "Rehoming Centres") {
            AdoptionLocationRow(location = "Dublin Rehoming Centre", contact = "dublin@pawrescu.example | (01) 555-0192")
            AdoptionLocationRow(location = "Cork Rehoming Centre", contact = "cork@pawrescu.example | (021) 555-0143")
            AdoptionLocationRow(location = "Galway Rehoming Centre", contact = "galway@pawrescu.example | (091) 555-0187")
            AdoptionLocationRow(location = "Limerick Rehoming Centre", contact = "limerick@pawrescu.example | (061) 555-0164")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AdoptionSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                content = content
            )
        }
    }
}

@Composable
fun AdoptionStepRow(
    step: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = step,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun AdoptionFeatureRow(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AdoptionRuleRow(
    time: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 1.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AdoptionLocationRow(
    location: String,
    contact: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = contact,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdoptionInfoScreenPreview() {
    PawRescuTheme {
        AdoptionInfoScreenContent()
    }
}
