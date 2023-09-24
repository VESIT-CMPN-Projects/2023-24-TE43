package com.example.empoweher.screen

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.example.empoweher.auth.signin.SignInScreen
import com.example.empoweher.auth.signin.SignInViewModel
import com.example.empoweher.model.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope,
) {

    val navController = rememberNavController()

    var shouldShowScaffold by remember {
        mutableStateOf(true)
    }

    /**
     * Checks if User is already logged in
     */
    val startDestination = if (googleAuthUiClient.getSignedInUser() != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                if (shouldShowScaffold) {
                    BottomNavigation(
                        navigateToItem = { route ->
                            navController.navigate(route = route)
                        }
                    )
                }
            }
        ) { paddingValues ->
            println(paddingValues)

            NavHost(navController = navController, startDestination = startDestination) {

                composable(Screen.Login.route) {
                    LaunchedEffect(key1 = Unit){
                        shouldShowScaffold = false
                    }
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == ComponentActivity.RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )
                    DisposableEffect(Unit) {
                        onDispose {
                            shouldShowScaffold = true
                        }
                    }

                    SignInScreen(
                        state = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        },
                        navigateToHome = {
                            navController.navigate(Screen.Home.route)
                            viewModel.resetState()
                        }
                    )
                }
                composable(route = Screen.Home.route) {
                    Home(
                        navigateToNextScreen = { route ->
                            navController.navigate(route)
                        }
                    )
                }
                composable(route = Screen.Safety.route) {
                    Safety(
                        navigateToNextScreen = { route ->
                            navController.navigate(route)
                        }
                    )
                }
                composable(route = Screen.FakeCall.route) {
                    LaunchedEffect(key1 = Unit){
                        shouldShowScaffold = false
                    }
                    FakeCall()
                    DisposableEffect(Unit) {
                        onDispose {
                            shouldShowScaffold = true
                        }
                    }
                }
                composable(route = Screen.EmergencyList.route) {
                    LaunchedEffect(key1 = Unit){
                        shouldShowScaffold = false
                    }
                    EmergencyList()
                    DisposableEffect(Unit) {
                        onDispose {
                            shouldShowScaffold = true
                        }
                    }
                }
            }
        }
    }
}