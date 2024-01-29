package com.example.empoweher.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.empoweher.viewmodel.QuoteViewModel

@Composable
fun Quote(
    viewModel: QuoteViewModel = QuoteViewModel()
){
    val value by remember {
        viewModel.repo
    }
    LaunchedEffect(key1=Unit){
        viewModel.getUsers()
    }
    Box(){
        LazyColumn(){
            items(value.quotes){
                Text(text = it.quote)
            }
        }
    }


}