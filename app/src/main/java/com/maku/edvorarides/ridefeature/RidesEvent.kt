package com.maku.edvorarides.ridefeature

// this is an action that the UI triggers
sealed class RidesEvent {
  object RequestLatestRidesList: RidesEvent()
}