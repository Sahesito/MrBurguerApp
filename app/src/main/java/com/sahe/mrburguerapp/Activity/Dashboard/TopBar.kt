package com.sahe.mrburguerapp.Activity.Dashboard

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.sahe.mrburguerapp.Activity.Splash.SplashActivity
import com.sahe.mrburguerapp.R
@Composable
fun TopBar() {
    val context = LocalContext.current
    var showLogoutDialog by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        // Creamos referencias para diferenciar cada composable
        val (name, settings, notification) = createRefs()

        // Imagen de las configuraciones - LOGOUT
        Image(
            painter = painterResource(R.drawable.settings_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(settings) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable {
                    // Mostrar diálogo de confirmación
                    showLogoutDialog = true
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(settings.end)
                    end.linkTo(notification.start)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto centrado del Dashboard
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append("MR")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("BURGUER")
                    }
                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Tienda Online",
                color = Color.DarkGray,
                fontSize = 14.sp
            )
        }

        // Imagen de la campanita (solo diseño)
        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { }
        )
    }

    // Diálogo de confirmación de logout
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Cerrar Sesión") },
            text = { Text("¿Estás seguro que deseas cerrar sesión?") },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        // Cerrar sesión
                        FirebaseAuth.getInstance().signOut()

                        // Redirigir a IntroActivity
                        val intent = Intent(context, SplashActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)

                        // Cerrar MainActivity
                        (context as? MainActivity)?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Sí, cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}