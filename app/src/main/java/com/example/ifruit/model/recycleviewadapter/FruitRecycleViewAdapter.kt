package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.recycleviewmodel.CostDataBaseModel
import com.example.ifruit.model.recycleviewmodel.DebtDataBaseModel
import com.example.ifruit.model.recycleviewmodel.EmployeeManagementDataBaseModel
import com.example.ifruit.model.recycleviewmodel.FruitDataBaseModel

class FruitRecycleViewAdapter(val context: Context, var fruitList: ArrayList<FruitDataBaseModel>,val itemClick : (FruitDataBaseModel) ->Unit) : RecyclerView.Adapter<FruitRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View,val itemClick : (FruitDataBaseModel) ->Unit) : RecyclerView.ViewHolder(itemView) {
        val fruitName = itemView.findViewById<TextView>(R.id.fruit_name)
        val fruitPrice = itemView.findViewById<TextView>(R.id.fruit_price)
        val fruitQuality = itemView.findViewById<TextView>(R.id.fruit_quality)

        fun bindProduct(fruitDataBaseModel: FruitDataBaseModel, context: Context) {

            fruitName.text=fruitDataBaseModel.name
            fruitPrice.text=fruitDataBaseModel.price.toString()
            fruitQuality.text=fruitDataBaseModel.qlt.toString()
            itemView.setOnClickListener {
                itemClick(fruitDataBaseModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_layout,parent,false)
        return ViewHolder(view,itemClick)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(fruitList[position],context)
    }

    override fun getItemCount(): Int {
        return fruitList.count()
    }
    fun updateFruitList(dataList : ArrayList<FruitDataBaseModel>){
        fruitList = dataList
        notifyDataSetChanged()
    }

}