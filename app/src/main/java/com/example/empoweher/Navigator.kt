package com.example.empoweher

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigator(){
    val navController= rememberNavController()
    NavHost(navController= navController, startDestination="Login"){
        composable(route="Home"){
            Home(navController)
        }
        composable(route="Login"){
            Login(navController)
        }
    }

}