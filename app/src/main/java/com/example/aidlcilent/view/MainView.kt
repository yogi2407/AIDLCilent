package com.example.aidlcilent.view

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

import com.example.aidlcilent.viewmodel.MainViewModel
import com.example.aidlcilent.viewmodel.NextScreenViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

@AndroidEntryPoint
class MainView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationScreenHandling()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun NavigationScreenHandling() {
        val mainScreenViewModel = hiltViewModel<MainViewModel>()
        val nextScreenViewModel = hiltViewModel<NextScreenViewModel>()
        val navController = rememberNavController()
        val animateNavController = rememberAnimatedNavController()
        Log.d("Yogi","NavigationScreenHandling Called")
       /* NavHost(navController = navController, startDestination = "Yogesh") {
            Log.d("Yogi","NavHost Called")
            composable(route = "Yogesh",) {
                Log.d("Yogi","MainViewScreen Called")
//                AnimatedVisibility(
//                    visible = true,
//                    enter = fadeIn(animationSpec = tween(700)),
//                    exit = fadeOut(animationSpec = tween(700))
//                ) {
//                    MainViewScreen(navController,mainScreenViewModel)
//                }
                MainViewScreen(navController,mainScreenViewModel)
            }
            composable(route = "Aarthi") {
                Log.d("Yogi","NextViewScreen Called")
//                AnimatedVisibility(
//                    visible = true,
//                    enter = fadeIn(animationSpec = tween(700)),
//                    exit = fadeOut(animationSpec = tween(700))
//                ) {
//                    NextViewScreen(navController,nextScreenViewModel)
//                }
               NextViewScreen(navController,nextScreenViewModel)
            }
        }*/

        AnimatedNavHost(navController = animateNavController, startDestination = Screen.mainscreen.route, builder = {
            composable(
                route = Screen.mainscreen.route,
                exitTransition = {
                    fadeOut(animationSpec = tween(3000))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(3000))
                }
            ) {
                MainViewScreen(navController,mainScreenViewModel)
            }
            composable(
                route = Screen.nextScreen.route,
                exitTransition = {
                    fadeOut(animationSpec = tween(3000))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(3000))
                }
            ) {
                NextViewScreen(navController,nextScreenViewModel)
            }
        })

    }



}



