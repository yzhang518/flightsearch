/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */

package com.example.flightsearch.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.flightsearch.data.Favorite




@Composable
fun DisplayFlightScreen(
    displayViewModel: DisplayFlightViewModel,
    searchViewModel: SearchFlightViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // val viewModel: DisplayFlightViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = displayViewModel.uiState.collectAsState().value
    //val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        UserInputField(
            input = uiState.departCode,
            onInputChange = { newInput ->
                // Handle navigation back to the search screen when input is cleared
                if (newInput.isEmpty()) {
                    searchViewModel.updateQuery("")
                    navController.popBackStack("Flight Search", inclusive = false)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DestinationList(
            departCode = uiState.departCode,
            departName = uiState.departName,
            destinationList = uiState.arrivalAirportList,
            favoriteList = uiState.favoriteList,
            onFavoriteClick = { depart: String, destination: String ->
                displayViewModel.addFavorite(depart, destination)
            }
        )
    }
}

@Composable
private fun DestinationList (
    departCode: String,
    departName: String,
    destinationList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier) {
    Column {
        Text (
            text = "Flights From $departCode",
            fontWeight = FontWeight.ExtraBold
        )
        LazyColumn(modifier = modifier) {
            items(items = destinationList) {item ->
                val isFavorite = favoriteList.any {
                    it.destination == item.code && it.departure == departCode
                }
                DestinationEntry(
                    departCode = departCode,
                    departName = departName,
                    entry = item,
                    isFavorite = isFavorite,
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier
                        .padding(4.dp)
                    //.clickable { onItemClick(item) }
                )

            }
        }
    }

}

@Composable
private fun DestinationEntry(
    departCode: String,
    departName: String,
    entry: Airport,
    isFavorite: Boolean,
    onFavoriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        var isCurrentFavorite = isFavorite
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(0.9f)) {
                Text(
                    text = "DEPART"
                )
                Row{
                    Text(
                        text = departCode,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = departName,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ARRIVE"
                )
                Row {
                    Text(
                        text = entry.code,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = entry.name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            IconButton(
                onClick = {
                    onFavoriteClick(departCode, entry.code)
                    //Log.d("Favorite", "isCurrentFavorite: $isCurrentFavorite")
                    isCurrentFavorite = !isCurrentFavorite

                    if (isCurrentFavorite) {
                        val message = "Added to favorites"
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }

                    //Log.d("Favorite", "after click: $isCurrentFavorite")
                },
                modifier = Modifier
                    .weight(0.1f)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    //imageVector = if (isCurrentFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    imageVector = if (isCurrentFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isCurrentFavorite) Color.Red else Color.Blue
                )
            }

        }
    }
}

