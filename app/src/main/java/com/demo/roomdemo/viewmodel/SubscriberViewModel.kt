package com.demo.roomdemo.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.roomdemo.respository.SubscriberRepository
import com.demo.roomdemo.subscriber.Subscriber
import kotlinx.coroutines.launch


class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    private var isUpdateOrDeleteEnabled = false
    private lateinit var updateOrDeleteSubscriber: Subscriber
    private val TAG = "SubscriberViewModel"
    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearOrDelete = MutableLiveData<String>()

    init {
        setInitialValues()
    }

    private fun setInitialValues() {
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDeleteEnabled = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDelete.value = "Clear All"


    }

    fun saveOrUpdate() {
         if(inputName.value!=null && inputEmail.value!= null) {

             if (isUpdateOrDeleteEnabled) {
                 val name: String = inputName.value!!
                 val email: String = inputEmail.value!!
                 if (name.isNotEmpty() && email.isNotEmpty()) {
                     updateOrDeleteSubscriber.name = name
                     updateOrDeleteSubscriber.email = email
                 }

                 updateSubscriber(updateOrDeleteSubscriber)

             } else {

                 val name: String = inputName.value!!
                 val email: String = inputEmail.value!!
                 if (name.isNotEmpty() && email.isNotEmpty()) {
                     insertSubscriber(Subscriber(0, name, email))
                 }

             }
         }


    }

    fun clearOrDelete() {
        if (isUpdateOrDeleteEnabled) {
            deleteSubscriber(updateOrDeleteSubscriber)
        } else {

            deleteAllSubscribers()
        }
    }

    private fun insertSubscriber(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.insertSubscriber(subscriber)
        }
        setInitialValues()
    }

    fun updateOrDelete(subscriber: Subscriber) {
        isUpdateOrDeleteEnabled = true
        updateOrDeleteSubscriber = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearOrDelete.value = "Delete"

        inputName.value = updateOrDeleteSubscriber.name
        inputEmail.value = updateOrDeleteSubscriber.email

    }

    private fun updateSubscriber(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.updateSubscriber(subscriber)

        }
        setInitialValues()
    }

    private fun deleteSubscriber(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.deleteSubscriber(subscriber)

        }
        setInitialValues()
    }

    private fun deleteAllSubscribers() {
        viewModelScope.launch {
            repository.deleteAllSubscriber()

        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}