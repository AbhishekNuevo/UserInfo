package com.demo.roomdemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.roomdemo.respository.SubscriberRepository
import com.demo.roomdemo.viewmodel.SubscriberViewModel
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val repository: SubscriberRepository):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
           return SubscriberViewModel(repository) as T
       }
        throw IllegalArgumentException("unknown view model class")
    }

}