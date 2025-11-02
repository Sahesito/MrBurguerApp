package com.sahe.mrburguerapp.Activity.Dashboard

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.sahe.mrburguerapp.Activity.ItemsList.ItemsListActivity
import com.sahe.mrburguerapp.Domain.CategoryModel
import com.sahe.mrburguerapp.R

@Composable
//Muestra las categorias
fun CategorySection(categories: SnapshotStateList<CategoryModel>, showCategoryLoading: Boolean){
    Text(
        text = "Elige una categoria",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    //Mientras cargan los datos, muestra una barra de carga
    if(showCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        //Una vez cargado, muestra las categorias
    } else {
        //Las agrupa en 3 columnas
        val rows = categories.chunked(3)
        val context = LocalContext.current

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            rows.forEach { rows ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rows.forEachIndexed { index, categoryModel ->

                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            onItemClick = {

                                val intent = Intent(context, ItemsListActivity::class.java).apply {
                                    putExtra("id", categoryModel.Id.toString())
                                    putExtra("title", categoryModel.Name)
                                }
                                startActivity(context, intent, null)
                            }
                        )
                    }
                    //Rellena espacios vacios si una fila tiene menos de 3
                    if (rows.size < 3 ){
                        repeat(3-rows.size){
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
//Item individual que muestra su imagen con su texto
fun CategoryItem(category: CategoryModel, modifier: Modifier = Modifier, onItemClick:()-> Unit){
    Column(modifier = modifier
        .fillMaxWidth()
        .background(
            color = colorResource(R.color.lightOrange),
            shape = RoundedCornerShape(13.dp)
        )
        .clickable(onClick = onItemClick)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.ImagePath,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = category.Name,
            color = colorResource(R.color.darkPurple),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

