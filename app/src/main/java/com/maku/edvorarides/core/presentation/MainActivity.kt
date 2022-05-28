package com.maku.edvorarides.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.maku.edvorarides.RideApp
import com.maku.edvorarides.ridefeature.RideViewModel
import com.maku.edvorarides.ridefeature.RidesEvent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val ridesViewModel: RideViewModel by viewModels()
    private val modifier = Modifier
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RideApp(ridesViewModel, modifier)
        }
        initData(ridesViewModel)
    }

    private fun initData(ridesViewModel: RideViewModel) {
        ridesViewModel.onEvent(RidesEvent.RequestLatestRidesList)
    }
}