package com.example.aidlcilent.view

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.aidlcilent.viewmodel.MainViewModel
import com.example.aidlcilent.viewmodel.NextScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationScreenHandling()
        }
    }


    @Composable
    fun NavigationScreenHandling() {
        val mainScreenViewModel = hiltViewModel<MainViewModel>()
        val nextScreenViewModel = hiltViewModel<NextScreenViewModel>()
        val navController = rememberNavController()
        Log.d("Yogi","NavigationScreenHandling Called")
        NavHost(navController = navController, startDestination = "Yogesh") {
            Log.d("Yogi","NavHost Called")
            composable(route = "Yogesh",) {
                Log.d("Yogi","MainViewScreen Called")
                MainViewScreen(navController,mainScreenViewModel)
            }
            composable(route = "Aarthi") {
                Log.d("Yogi","NextViewScreen Called")
                 NextViewScreen(navController,nextScreenViewModel)
            }
        }



    }



}



