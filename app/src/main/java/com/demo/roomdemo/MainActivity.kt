package com.demo.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.roomdemo.databinding.ActivityMainBinding
import com.demo.roomdemo.factory.SubscriberViewModelFactory
import com.demo.roomdemo.respository.SubscriberRepository
import com.demo.roomdemo.subscriber.Subscriber
import com.demo.roomdemo.subscriber.SubscriberDao
import com.demo.roomdemo.subscriber.SubscriberDatabase
import com.demo.roomdemo.viewmodel.SubscriberViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao:SubscriberDao = SubscriberDatabase.getDataBaseInstance(applicationContext).subscriberDao()
        val subscriberRepository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(subscriberRepository)
        subscriberViewModel =  ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        subscriberViewModel = SubscriberViewModel(subscriberRepository)

        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

     private fun initRecyclerView(){
         binding.rcSubscriber.layoutManager = LinearLayoutManager(this)
         displaySubscriberList()
     }
    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer { it ->
            binding.rcSubscriber.adapter = SubscriberItemAdapter(it,itemSelected)
        })

    }

    private val itemSelected  = { sub: Subscriber ->
        subscriberViewModel.updateOrDelete(sub)

    }
//    fun itemSelected(subscriber: Subscriber){
//
//    }
}