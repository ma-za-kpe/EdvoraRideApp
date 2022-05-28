package com.maku.edvorarides.ridefeature

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maku.edvorarides.core.presentation.Event
import com.maku.edvorarides.ui.components.FilterSection
import com.maku.edvorarides.ui.components.RideScreenState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RideScreen(ridesViewModel: RideViewModel, modifier: Modifier, colors: Colors, context: Context) {
    val state = ridesViewModel.state.observeAsState()
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp, 0.dp, 8.dp, 8.dp)) {
        
        HandleLoading(state.value?.loading)
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    text = "Rides:",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 6.dp
                    )
                )
            }
            items(state.value!!.categories){ item ->
                HandleFilterItems(item, ridesViewModel, colors, modifier, state.value!!.selectedFilter)
            }
        }

        HandleRide(state.value, colors, modifier, context, ridesViewModel)

        HandleFailures(state.value?.failure, context)
    }
}

@Composable
fun HandleFilterItems(
    item: UIFilter,
    ridesViewModel: RideViewModel,
    colors: Colors,
    modifier: Modifier,
    selectedFilter: String,
) {
    FilterSection(
        setSelected = { name, selected ->
            run {
                ridesViewModel.setSelectedFilter(
                    name,
                    selected
                )
            }
        },
        selected = selectedFilter == item.name,
        category = item,
        modifier,
        colors
    ) {

    }
}

@Composable
fun HandleRide(
    state: RidesViewState?,
    colors: Colors,
    modifier: Modifier,
    context: Context,
    ridesViewModel: RideViewModel,
) {

    // you can use filter here only if you convert date using the threeten library before saving in the room db
    when (state?.selectedFilter) {
        "Upcoming" -> {
            state.rides.sortedBy { it.distance }.forEach { ride ->
                if (convertDate(ride.date).after(Date())){
                    RideScreenState(ride, colors, modifier, context)
                }
            }
        }
        "Past" -> {
            state.rides.sortedBy { it.distance }.forEach { ride ->
                if (convertDate(ride.date).before(Date())){
                    RideScreenState(ride, colors, modifier, context)
                }
            }
        }
        "Past" -> {
            Toast.makeText(context, "Feature coming very soon", Toast.LENGTH_SHORT).show()
        }
        else -> {
            // Nearest is our default selected, naturally just sort according to distance
            state?.rides?.sortedBy { it.distance }?.forEach { ride ->
                // TODO: find better way to handle filter by nearest (several recompositions... idk)
                ridesViewModel.updateRide(distance(state.user.station_code, ride.station_path).toString(), ride.id)
                // TODO: filter list here using the value of state selected in an if else
                RideScreenState(ride, colors, modifier, context)
            }
        }
    }

}

// TODO: thi can be an extention function
fun convertDate(date: String): Date {
    val sdf = SimpleDateFormat("MM/DD/YY hh:mm aaa", Locale.ENGLISH)
    Log.d("rs", "date ${sdf.parse(date)}")
    return sdf.parse(date) as Date
}

fun distance(stationCode: Int, stationPath: List<Int>): Int{
    // distance = nearest number to station code and bigger than station code - station code
    // 1. order numbers in ascending orders
    // 2. filter numbers greater than or equal to station code, and save in new array
    // 3. get the first value of that array

    val arr = stationPath.sorted().filter { it > stationCode || it == stationCode }
    return arr[0] - stationCode
}


@Composable
fun HandleLoading(loading: Boolean?) {
    if (loading == true){
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
            )
         }
    }
}

@Composable
fun HandleFailures(failure: Event<Throwable>?, context: Context) {
    val unhandledFailure = failure?.getContentIfNotHandled() ?: return

    val fallbackMessage = "An error : "

    val errorMessage = if (unhandledFailure.message.isNullOrEmpty()) {
        fallbackMessage
    } else {
        unhandledFailure.message!!
    }
    if (errorMessage.isNotEmpty()) {
        Text(text = "Error : ${unhandledFailure.message}")
    }
}
