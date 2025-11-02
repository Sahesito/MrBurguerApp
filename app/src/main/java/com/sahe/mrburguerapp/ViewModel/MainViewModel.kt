package com.sahe.mrburguerapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sahe.mrburguerapp.Domain.BannerModel
import com.sahe.mrburguerapp.Repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    fun loadBanner() : LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }
}