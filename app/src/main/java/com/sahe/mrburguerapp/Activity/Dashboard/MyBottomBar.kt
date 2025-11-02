package com.sahe.mrburguerapp.Activity.Dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sahe.mrburguerapp.R
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun MyBottomBar(){
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar (
        backgroundColor = colorResource(R.color.grey),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEach { bottomMenuItem ->
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.label),
                onClick = {
                    selectedItem = bottomMenuItem.label
                    if (bottomMenuItem.label == "Cart") {

                    } else {

                    }
                },
                    icon = {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(20.dp))
                    }
            )
        }
    }
}
data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem>{
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Home", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomMenuItem(label = "Cart", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(BottomMenuItem(label = "Favorite", icon = painterResource(R.drawable.btn_3)))
    bottomMenuItemList.add(BottomMenuItem(label = "Order", icon = painterResource(R.drawable.btn_4)))
    bottomMenuItemList.add(BottomMenuItem(label = "Profile", icon = painterResource(R.drawable.btn_5)))

    return bottomMenuItemList
}