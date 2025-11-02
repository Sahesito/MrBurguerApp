package com.sahe.mrburguerapp.Activity.ItemsList

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sahe.mrburguerapp.Activity.BaseActivity
import com.sahe.mrburguerapp.R
import com.sahe.mrburguerapp.ViewModel.MainViewModel

class ItemsListActivity : BaseActivity() {

    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Manejan nulls
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""

        //Muestra el contenido
        setContent {
            ItemsListScreen(
                title = title,
                onBackClick = {finish()},
                viewModel = viewModel,
                id = id
            )
        }
    }
}

@Composable
private fun ItemsListScreen(
  title: String,
  onBackClick: () -> Unit,
  viewModel: MainViewModel,
  id: String
) {

    //Cargan los datos
    val items by viewModel.loadFiltered(id).observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }


    //Se aseguran que los datos carguen correctamente
    LaunchedEffect(id) {
        viewModel.loadFiltered(id)
    }
    LaunchedEffect(items) {
        isLoading = items.isEmpty()
    }
    Column(modifier = Modifier.fillMaxSize()){
        ConstraintLayout(
            modifier = Modifier.padding(top = 36.dp, start = 16.dp, end = 16.dp)
        ) {
            //Se referencian para ubicarlos
            val (backBtn, cartTxt) = createRefs()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt){
                        centerTo(parent)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                text = title
            )

            //Icono de retroceso
            Image(painter = painterResource(R.drawable.back_grey),
                contentDescription = null,
                modifier = Modifier
                    .clickable{
                        onBackClick()
                    }
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }

        //Mientras se cargan los datos, muestra la barra de carga
        if(isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Una vez cargada muestra los items
            ItemsList(items)
        }
    }
}