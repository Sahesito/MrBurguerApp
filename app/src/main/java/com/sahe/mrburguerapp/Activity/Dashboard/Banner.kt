package com.sahe.mrburguerapp.Activity.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.sahe.mrburguerapp.Domain.BannerModel
import com.sahe.mrburguerapp.R

@Composable
fun Banner(banners: SnapshotStateList<BannerModel>, showBannerLoading: Boolean){
    //Muestra un indicador de carga mientras se cargan los datos
    if(showBannerLoading){
        Box(modifier = Modifier
            .fillMaxSize()
            .height(200.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        //Una vez cargado, muestra las imagenes
        Banners(banners = banners)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banners(banners: List<BannerModel>){
    AutoSlidingCarousel(banners = banners)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
//Función del Carrusel
fun AutoSlidingCarousel(modifier: Modifier = Modifier,
                        pagerState: PagerState = remember { PagerState() },
                        banners: List<BannerModel>){

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(count =  banners.size, state = pagerState) {
            page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)
            )
        }
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
                totalDots = banners.size,
                selectedIndex = if(isDragged)pagerState.currentPage else pagerState.currentPage,
                dotSize = 8.dp
            )
    }
}

@Composable
// Formato para los puntitos
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.orange),
    unSelectedColor: Color = colorResource(R.color.grey),
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
                IndicatorDot(
                    // El puntito de la imagen que está selecionada se pinta, si se cambia, se cambia de color
                    color = if (index == selectedIndex) selectedColor else unSelectedColor,
                    size = dotSize
                )
                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
        }
    }
}

@Composable
// Formato de un puntito
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
){
    Box(modifier = modifier
        .size(size)
        .clip(CircleShape)
        .background(color)
    )
}