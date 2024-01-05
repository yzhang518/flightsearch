/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.setValue


// to be used by both screens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputField(
    input: String,
    onInputChange: (String) -> Unit = {}
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(input)) }
    OutlinedTextField(
        value = textFieldValue.text,
        onValueChange = {
            textFieldValue = textFieldValue.copy(text = it)
            onInputChange(it)
        },
        label = { Text("Enter Departure Airport") },
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
        singleLine = true
    )

}