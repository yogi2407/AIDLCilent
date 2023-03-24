package com.example.aidlcilent.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aidlcilent.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers

@Composable
fun MainViewScreen(navController: NavHostController, viewModel: MainViewModel) {
    val dataChanged by viewModel.coreConnectionStateChanged.collectAsState(Dispatchers.Default)
    val btToggleState by viewModel.btToggleState.collectAsState(Dispatchers.Default)
    val deviceToggleState by viewModel.deviceToggleState.collectAsState(Dispatchers.Default)
    val ruiToggleState by viewModel.ruiToggleState.collectAsState(Dispatchers.Default)
    val wifiToggleState by viewModel.wifiToggleState.collectAsState(Dispatchers.Default)
    val visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter =  fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = fadeOut()
    ) {
        Text("AIDL Client ", Modifier.fillMaxWidth().height(200.dp))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = { viewModel.onConnectCoreService() }) {
                    Text(text = "OnConnectService")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = dataChanged)
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "BT Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Switch(checked = btToggleState, onCheckedChange = {
                        viewModel.onBtToggleClicked()
                    })
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Device Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Switch(checked = deviceToggleState, onCheckedChange = {
                        viewModel.onDeviceToggleClicked()
                    })
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "RUI Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Switch(checked = ruiToggleState, onCheckedChange = {
                        viewModel.onRuiToggleClicked()
                    })
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Wifi Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Switch(checked = wifiToggleState, onCheckedChange = {
                        viewModel.onWifiToggleClicked()
                    })

                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navController.navigate("Aarthi")}) {
                    Text(text = "Next Screen")
                }




            }
        }
    }
}