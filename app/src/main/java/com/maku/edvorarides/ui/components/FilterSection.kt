package com.maku.edvorarides.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Colors
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maku.edvorarides.ridefeature.UIFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FilterSection(
    setSelected: (String, Boolean) -> Unit,
    selected: Boolean,
    category: UIFilter,
    modifier: Modifier,
    colors: Colors,
    doSearch: () -> Unit,
    ){
            Box(
                modifier = Modifier
                    .toggleable(
                        value = selected,
                        onValueChange = {
                            setSelected(category.name, true)
                            doSearch()
                        }
                    )
            ) {
                if (category.name !="Filter"){
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .wrapContentSize()
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                    ){
                        Text(
                            text = category.name,
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = Modifier.padding(
                                horizontal = 2.dp,
                                vertical = 6.dp
                            ),
                            style = if (selected) TextStyle(textDecoration = TextDecoration.Underline) else TextStyle(textDecoration = TextDecoration.None)
                        )
                        Text(
                            text = "(${category.number})",
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = Modifier.padding(
                                horizontal = 2.dp,
                                vertical = 6.dp
                            ),
                            style = if (selected) TextStyle(textDecoration = TextDecoration.Underline) else TextStyle(textDecoration = TextDecoration.None)
                        )
                    }

                } else {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .wrapContentSize()
                            .padding(3.dp, 0.dp, 3.dp, 0.dp)
                    ) {
                        Image(
                            imageVector = Icons.Filled.List,
                            colorFilter = ColorFilter.tint(colors.onBackground),
                            contentDescription = "filter",
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = category.name,
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = Modifier.padding(
                                horizontal = 3.dp,
                                vertical = 6.dp
                            ),
                            style = if (selected) TextStyle(textDecoration = TextDecoration.Underline) else TextStyle(textDecoration = TextDecoration.None)

                        )
                    }
                }
            }

    }
