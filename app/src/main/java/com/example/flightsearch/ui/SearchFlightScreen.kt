// allow users to search flight
/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */

package com.example.flightsearch.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

@Composable
fun SearchFlightScreen(
    searchViewModel: SearchFlightViewModel,
    onItemClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    //val viewModel: SearchFlightViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = searchViewModel.uiState.collectAsState().value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        UserInputField (
            uiState.searchBy,
            //"test",
            onInputChange = {
                searchViewModel.updateQuery(it)
                //viewModel.updateSelectedAirports(it)
            }
        )

        if (uiState.searchBy.isNotEmpty()) {
            AirportList (
                airportList = uiState.selectedAirports,
                //airportList = DataSource.testAirportList,
                onItemClick = { onItemClick(it) },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        } else {
            // TODO: display favorite here
            if (uiState.favoriteList.isNotEmpty()) {
                FavoriteList(
                    favList = uiState.favoriteList,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

        }
    }
}



@Composable
private fun AirportList (
    airportList: List<Airport>,
    onItemClick: (Airport) -> Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = airportList) {item ->
            AirportEntry(
                entry = item,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onItemClick(item) }
            )

        }
    }
}

@Composable
private fun AirportEntry(
    entry: Airport, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = entry.code,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = entry.name
            )
        }

    }
}

@Composable
private fun FavoriteList (
    favList: List<Favorite>,
    modifier: Modifier = Modifier) {
    Text (
        text = "Favorite Routes",
        fontWeight = FontWeight.ExtraBold
    )
    LazyColumn(modifier = modifier) {
        items(items = favList) {item ->
            FavoriteEntry(
                entry = item,
                modifier = Modifier
                    .padding(4.dp)
            )

        }
    }
}

@Composable
private fun FavoriteEntry(
    entry: Favorite, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        var isCurrentFavorite = true
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "DEPART"
                )
                Row{
                    Text(
                        text = entry.departure,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ARRIVE"
                )
                Row {
                    Text(
                        text = entry.destination,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
            IconButton(
                onClick = {
                    Log.d("Favorite", "isCurrentFavorite: $isCurrentFavorite")
                },
                modifier = Modifier.padding(end = 16.dp),
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

