package com.maku.edvorarides.ridefeature

import com.maku.edvorarides.core.data.local.models.RideResponseItem
import com.maku.edvorarides.core.presentation.Event
import com.maku.edvorarides.ridefeature.domain.model.User

// this is the class that stores the current state of your View.
data class RidesViewState(
    //user
    val user: User = User(40, "Dhruv Singh", "url"),

    // filter
    val selected: Boolean = false,
    val selectedFilter: String = "Nearest",
    val categories: List<UIFilter> = emptyList(),

    // list
    val loading: Boolean = true,
    val rides: List<RideResponseItem> = emptyList(),

    // Using Event prevents your app from handling the error more than once.
    val failure: Event<Throwable>? = null
) {
    fun setSelectedFilter(name: String, selected: Boolean): RidesViewState {
        return copy(
            selectedFilter = name,
            selected = selected
        )
    }
}

data class NearestFilter(
    val id: Int,
    val distance: Int
)

data class UIFilter(
    val name: String,
    val number: Int
)