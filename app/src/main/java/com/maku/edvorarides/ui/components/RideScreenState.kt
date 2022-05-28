package com.maku.edvorarides.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maku.edvorarides.R
import com.maku.edvorarides.core.data.local.models.RideResponseItem


@Composable
fun RideScreenState(
    ride: RideResponseItem,
    colors: Colors,
    modifier: Modifier,
    context: Context,
) {
            // TODO: don't forget to handle state ... loading, failure, etc

            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                backgroundColor = colors.background,
                contentColor = colors.onBackground,
                modifier = modifier.size(440.dp),
            ) {

                Column(modifier = modifier.padding(10.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(ride.map_url)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R .drawable.ic_error_placeholder),
                        contentDescription = stringResource(R.string.url),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                    
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = ride.city,
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        Text(
                            text = ride.state,
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )

                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Ride id",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        Text(
                            text = ride.id.toString(),
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )

                        )

                    }


                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Origin Station",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        Text(
                            text = ride.origin_station_code.toString(),
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )

                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Station path",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        ride.station_path.forEach { path ->
                            Text(
                                text = "[$path]",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                maxLines = 1,
                                modifier = modifier
                                    .padding(
                                        horizontal = 2.dp,
                                        vertical = 6.dp
                                    )

                            )
                        }

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Date",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        Text(
                            text = ride.date,
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )

                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Distance",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )
                        )

                        Text(
                            text = ride.distance.toString(),
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = modifier
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 6.dp
                                )

                        )

                    }
                }

            }
    
            Spacer(modifier = modifier.size(10.dp))
        }
