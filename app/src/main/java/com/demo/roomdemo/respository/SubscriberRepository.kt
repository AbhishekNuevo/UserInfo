package com.demo.roomdemo.respository

import com.demo.roomdemo.subscriber.Subscriber
import com.demo.roomdemo.subscriber.SubscriberDao

class SubscriberRepository(private val subscriberDao: SubscriberDao) {

    val subscribers = subscriberDao.getAllSubscribers()

    suspend fun insertSubscriber(subscriber: Subscriber){
        subscriberDao.insertSubscriber(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber){
        subscriberDao.updateSubscriber(subscriber)
    }

    suspend fun deleteSubscriber(subscriber: Subscriber){
        subscriberDao.deleteSubscriber(subscriber)
    }
    suspend fun deleteAllSubscriber(){
        subscriberDao.deleteAllSubscriber()
    }


}