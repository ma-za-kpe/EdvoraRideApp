package com.maku.edvorarides

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maku.edvorarides.ridefeature.RideScreen
import com.maku.edvorarides.ridefeature.RideViewModel
import com.maku.edvorarides.ui.theme.EdvoraRidesTheme
import timber.log.Timber

@Composable
fun RideApp(ridesViewModel: RideViewModel, modifier: Modifier) {
    val colors = MaterialTheme.colors
    val context = LocalContext.current

    // initialise distance

    EdvoraRidesTheme {
        AppContent(ridesViewModel, modifier, colors, context)
    }
}

@Composable
fun AppContent(ridesViewModel: RideViewModel, modifier: Modifier, colors: Colors, context: Context) {

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(
                        text = "Edvora",
                        fontSize = 30.sp,
                        color = colors.onBackground
                    )
            },
                actions = {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "profile image",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                },
                backgroundColor = colors.background,
                contentColor = colors.onBackground
            )
        },
        content = {
            RideScreen(ridesViewModel = ridesViewModel, modifier = modifier, colors, context)
        }
    )

}