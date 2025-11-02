package com.sahe.mrburguerapp.Activity.Splash

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahe.mrburguerapp.R

@Composable
@Preview
fun GetStartedButtons(
    onClick:()->Unit={}, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        //Boton de ingreso el cual nos lleva al Dashboard
        Button(
            onClick = { onClick() }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orange)
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Ingresar",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
