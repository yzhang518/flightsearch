/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.flightsearch.ui.SearchFlightScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.DisplayFlightScreen
import com.example.flightsearch.ui.DisplayFlightViewModel
import com.example.flightsearch.ui.SearchFlightViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchBar(
    // currentScreenTitle: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Flight Search") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchApp(
    displayViewModel: DisplayFlightViewModel = viewModel(factory = AppViewModelProvider.Factory),
    searchViewModel: SearchFlightViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen

    /*
        val currentScreen = FlightSearchAppScreen.valueOf(
            backStackEntry?.destination?.route ?: FlightSearchAppScreen.Start.name
        )
    */
    Scaffold(
        topBar = {
            FlightSearchBar(
                //currentScreenTitle = uiState.screenTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Flight Search",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route="Flight Search") {
                SearchFlightScreen(
                    //airportList =  DataSource.testAirportList,
                    searchViewModel = searchViewModel,
                    onItemClick = {
                        displayViewModel.setDepartureCode(it.code)
                        displayViewModel.setDepartureName(it.name)
                        displayViewModel.setArrival(it.id)
                        navController.navigate("Flight Display")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

            composable(route="Flight Display") {
                DisplayFlightScreen(
                    displayViewModel = displayViewModel,
                    searchViewModel = searchViewModel,
                    navController = navController
                )
            }
        }
    }
}