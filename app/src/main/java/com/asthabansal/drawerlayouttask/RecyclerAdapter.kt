package com.asthabansal.drawerlayouttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var count = 1
    fun updateCount(count:Int){
        this.count = count
        notifyDataSetChanged()
    }
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var ItemGridView = view.findViewById<View>(R.id.gridView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent ,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= this.count

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}