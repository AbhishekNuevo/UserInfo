package com.demo.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.roomdemo.databinding.ListItemBinding
import com.demo.roomdemo.subscriber.Subscriber

class SubscriberItemAdapter(private val subscriberList: List<Subscriber>, val onClickListener : (Subscriber)->Unit): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
         val inflater =   LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(inflater,R.layout.list_item,parent,false)
        return ItemViewHolder(binding,onClickListener)
    }

    override fun getItemCount(): Int {
       return subscriberList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       holder.bind(subscriberList[position])
    }


}

class ItemViewHolder(private val binding: ListItemBinding,val onClickItem : (Subscriber)->Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber){
        binding.tvName.text = subscriber.name
        binding.tvEmail.text = subscriber.email
        binding.cardItem.setOnClickListener {
            onClickItem(subscriber)
        }
    }


}