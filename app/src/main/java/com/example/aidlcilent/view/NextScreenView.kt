package com.example.aidlcilent.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aidlcilent.viewmodel.NextScreenViewModel
import kotlinx.coroutines.Dispatchers


@Composable
fun NextViewScreen(navController: NavHostController, viewModel: NextScreenViewModel) {

    val btToggleState by viewModel.btToggleState.collectAsState(Dispatchers.Default)
    val deviceToggleState by viewModel.deviceToggleState.collectAsState(Dispatchers.Default)
    val ruiToggleState by viewModel.ruiToggleState.collectAsState(Dispatchers.Default)
    val wifiToggleState by viewModel.wifiToggleState.collectAsState(Dispatchers.Default)

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
                  Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "BT Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Checkbox(checked = btToggleState, onCheckedChange = {
                        viewModel.onBtToggleClicked()
                    } )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Device Toggle")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Checkbox(checked = deviceToggleState, onCheckedChange = {
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
                    Checkbox(checked = ruiToggleState, onCheckedChange = {
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
                    Checkbox(checked = wifiToggleState, onCheckedChange = {
                        viewModel.onWifiToggleClicked()
                    })

                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navController.navigate("Yogesh")}) {
                    Text(text = "Back")
                }

            }
        }
    }
}
