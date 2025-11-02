package com.sahe.mrburguerapp.Activity.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.sahe.mrburguerapp.Activity.BaseActivity
import com.sahe.mrburguerapp.Activity.Dashboard.MainActivity
import com.sahe.mrburguerapp.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar si viene del logout
        val fromLogout = intent.getBooleanExtra("FROM_LOGOUT", false)

        // Si no viene del logout verifica autenticación
        if (!fromLogout) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                // Usuario ya logueado, va a MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return
            }
        }

        // Mostrar el contenido (pantalla de bienvenida con botones)
        setContent {
            SplashScreen()
        }
    }
}

@Composable
@Preview
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkBrown))
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(top = 48.dp)
        ) {
            // Imagenes de fondo y logo
            val (backgroundImg, logoImg) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.intro_pic),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(R.drawable.pizza),
                contentDescription = null,
                modifier = Modifier.constrainAs(logoImg) {
                    top.linkTo(backgroundImg.top)
                    bottom.linkTo(backgroundImg.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                contentScale = ContentScale.Fit
            )
        }

        // Titulo
        val styledText = buildAnnotatedString {
            append("Bienvenidos a\n")
            withStyle(style = SpanStyle(color = colorResource(R.color.orange))) {
                append("MrBurguer")
            }
        }

        // Formato al texto
        Text(
            text = styledText,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp)
        )

        // Imprime el splashSubtitulo
        Text(
            text = stringResource(R.string.splashSubtitulo),
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        GetStartedButtons(
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}