package com.sahe.mrburguerapp.Activity.DetalleComida

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahe.mrburguerapp.R

@Composable
//Descripcion de cada plato
fun DescriptionSection(description: String){
    Column {
        Text(
            text = "Detalle",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.padding(16.dp)
        )
    }
}