package com.sahe.mrburguerapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sahe.mrburguerapp.Domain.BannerModel
import com.sahe.mrburguerapp.Domain.CategoryModel
import com.sahe.mrburguerapp.Domain.FoodModel
import com.sahe.mrburguerapp.Repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()
//Expone los datos de la UI utilizando el LiveData
    fun loadBanner() : LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadCategory() : LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadFiltered(id:String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }
}