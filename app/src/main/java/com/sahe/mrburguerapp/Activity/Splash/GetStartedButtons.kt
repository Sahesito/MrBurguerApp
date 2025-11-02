package com.sahe.mrburguerapp.Activity.Splash

import android.content.Intent
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahe.mrburguerapp.Activity.Auth.AuthActivity
import com.sahe.mrburguerapp.R

@Composable
fun GetStartedButtons(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Botón de Login
        Button(
            onClick = {
                val intent = Intent(context, AuthActivity::class.java).apply {
                    putExtra("AUTH_MODE", "signin")
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(0.35f)
                .border(1.dp, Color.White, shape = RoundedCornerShape(50.dp))
                .height(50.dp)
        ) {
            Text(text = "Login", fontSize = 16.sp, color = Color.White)
        }

        // Botón de Registro
        Button(
            onClick = {
                val intent = Intent(context, AuthActivity::class.java).apply {
                    putExtra("AUTH_MODE", "signup")
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orange)
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun GetStartedButtonsPreview() {
    GetStartedButtons()
}