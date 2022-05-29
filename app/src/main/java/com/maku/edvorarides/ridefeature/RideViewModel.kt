package com.maku.edvorarides.ridefeature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.edvorarides.core.data.local.models.RideResponseItem
import com.maku.edvorarides.core.data.remote.interceptors.NetworkException
import com.maku.edvorarides.core.data.repositories.RideRepository
import com.maku.edvorarides.core.presentation.Event
import com.maku.edvorarides.core.utils.createExceptionHandler
import com.maku.edvorarides.ridefeature.usecases.GetCachedRides
import com.maku.edvorarides.ridefeature.usecases.GetNetworkRides
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RideViewModel @Inject constructor(
    private val getCachedRides: GetCachedRides,
    private val getNetworkRides: GetNetworkRides,
    private val compositeDisposable: CompositeDisposable,

    // TODO: remove this bad practice(broken window) from here
    private val repository: RideRepository
): ViewModel() {

    fun updateRide(distance: String, id: Int){
        viewModelScope.launch {
            repository.updateRide(distance, id)
        }
    }

    val state: LiveData<RidesViewState> get() = _state
    private val _state = MutableLiveData<RidesViewState>()

    init {
        _state.value = RidesViewState()
        setUpFilter()
        subscribeToRideDbUpdates()
    }

    private fun subscribeToRideDbUpdates() {
        getCachedRides()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onRides(it) },
                { onFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun onRides(it: List<RideResponseItem>) {
        // if rides table is empty, trigger the network event
         _state.value = state.value!!.copy( loading = true)
        if (it.isNotEmpty()){
            Log.d("vm", "called onRides not ${it.size}")
            val list = it.sortedBy { it.distance }
            _state.value = state.value!!.copy(loading = false, rides = list)
        } else {
            Log.d("vm", "called onRides empty ${it.size}")
            loadRides()
        }
    }


    fun setSelectedFilter(name: String, selected: Boolean){
        _state.value = state.value!!.setSelectedFilter(name, selected)
    }

    private fun setUpFilter() {
        val filterItems = ArrayList<UIFilter>()
        filterItems.add(UIFilter("Nearest", 10))
        filterItems.add(UIFilter("Upcoming",4))
        filterItems.add(UIFilter("Past", 5))
        filterItems.add(UIFilter("Filter", 0))
        _state.value = state.value!!.copy(categories = filterItems)
    }

    // trigger this from the ui
    fun onEvent(event: RidesEvent) {
        when(event) {
            is RidesEvent.RequestLatestRidesList ->
                loadRides()
        }
    }

    private fun loadRides() {
        Log.d("vm", "called loadRides ${state.value?.rides?.size}")
        if (state.value!!.rides.isEmpty()) {
            loadRidesFromNetwork()
        }
    }

    private fun loadRidesFromNetwork() {
        Log.d("vm", "called loadRidesFromNetwork ${state.value?.rides?.size}")
        _state.value = state.value!!.copy( loading = true)
        val errorMessage = "Failed to fetch rides: "
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage){
            onFailure(it)
        }
        viewModelScope.launch(exceptionHandler) {
            delay(3000)
            getNetworkRides()
            _state.value = state.value!!.copy( loading = false)
        }
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException -> {
                _state.value = state.value!!.copy(
                    loading = false,
                    failure = Event(failure)
                )
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}