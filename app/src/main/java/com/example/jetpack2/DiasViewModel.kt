package com.example.jetpack2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack2.Models.Dia
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DiasViewModel: ViewModel() {

    private val currentDia: MutableLiveData<Dia> by lazy {
        MutableLiveData<Dia>()
    }

    fun getCurrentDia(): Dia? {
        return currentDia.value
    }

    fun getFormattedDate():String{
        var format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(currentDia.value!!.data)
    }

    fun setCurrentDia(dia: Dia){
        currentDia.value = dia
    }

}