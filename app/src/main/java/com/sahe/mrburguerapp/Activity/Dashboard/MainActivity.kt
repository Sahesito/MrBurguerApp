package com.sahe.mrburguerapp.Activity.Dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sahe.mrburguerapp.Activity.BaseActivity
import com.sahe.mrburguerapp.Domain.BannerModel
import com.sahe.mrburguerapp.Domain.CategoryModel
import com.sahe.mrburguerapp.ViewModel.MainViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Controlador de todo el Dashboard
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){

    val scaffoldState = rememberScaffoldState()
    val viewModel = MainViewModel()

    //Datos
    val banners = remember { mutableStateListOf<BannerModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>()}

    //Pantallas de carga
    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }



    LaunchedEffect(Unit){
        viewModel.loadBanner().observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

//Scaffold nos brinda una estructura para el Dashboard
    Scaffold(bottomBar =  { MyBottomBar() },
        scaffoldState = scaffoldState) {
        paddingValues ->

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
        ) {
            //LLama a los Files para mostrarlos
            item {
                TopBar()
            }

            item {
                Banner(banners, showBannerLoading)
            }

            item{
                Search()
            }

            item {
                CategorySection(categories, showCategoryLoading)
            }
        }
    }
}