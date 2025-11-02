package com.sahe.mrburguerapp.Activity.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sahe.mrburguerapp.Activity.BaseActivity
import com.sahe.mrburguerapp.Activity.Dashboard.MainActivity
import com.sahe.mrburguerapp.ui.theme.MrBurguerAppTheme

class AuthActivity : BaseActivity() {
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Obtener el modo desde el intent (si viene de los botones)
        val initialMode = intent.getStringExtra("AUTH_MODE") ?: "signin"

        setContent {
            MrBurguerAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AuthScreen(
                        viewModel = viewModel,
                        initialMode = if (initialMode == "signup") AuthMode.SignUp else AuthMode.SignIn,
                        onAuthSuccess = {
                            // Navegar a MainActivity o donde corresponda
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    initialMode: AuthMode = AuthMode.SignIn,
    onAuthSuccess: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    var currentMode by remember { mutableStateOf(initialMode) }

    // Manejar estados de autenticación
    LaunchedEffect(state.user) {
        if (state.user != null) {
            Toast.makeText(
                context,
                "¡Bienvenido!",
                Toast.LENGTH_SHORT
            ).show()
            onAuthSuccess()
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    SignInScreen(
        mode = currentMode,
        onPrimary = { email, password ->
            when (currentMode) {
                AuthMode.SignIn -> viewModel.signIn(email, password)
                AuthMode.SignUp -> viewModel.signUp(email, password)
            }
        },

        onForgotPassword = {
            Toast.makeText(
                context,
                "Funcionalidad de recuperación próximamente",
                Toast.LENGTH_SHORT
            ).show()
        },

        onForgotNumber = {
            Toast.makeText(
                context,
                "Funcionalidad de recuperación de número próximamente",
                Toast.LENGTH_SHORT
            ).show()
        },

        onSwitch = {
            currentMode = if (currentMode == AuthMode.SignIn) {
                AuthMode.SignUp
            } else {
                AuthMode.SignIn
            }
        }
    )
}