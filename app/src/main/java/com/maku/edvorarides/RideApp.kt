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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maku.edvorarides.ridefeature.RideScreen
import com.maku.edvorarides.ridefeature.RideViewModel
import com.maku.edvorarides.ridefeature.RidesEvent
import com.maku.edvorarides.ui.theme.EdvoraRidesTheme

@Composable
fun RideApp(modifier: Modifier) {
    val colors = MaterialTheme.colors
    val context = LocalContext.current
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    EdvoraRidesTheme {
        AppContent(modifier, colors, context, scaffoldState)
    }
}

@Composable
fun AppContent(
    modifier: Modifier,
    colors: Colors,
    context: Context,
    scaffoldState: ScaffoldState
) {

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
            RideScreen(modifier = modifier, colors = colors, context = context, scaffoldState = scaffoldState)
        }
    )

}