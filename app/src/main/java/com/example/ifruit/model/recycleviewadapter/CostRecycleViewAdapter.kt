package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.recycleviewmodel.CostDataBaseModel

class CostRecycleViewAdapter(
    val context: Context,
    var costList: ArrayList<CostDataBaseModel>,
    val itemClick: (CostDataBaseModel) -> Unit
) : RecyclerView.Adapter<CostRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, val itemClick: (CostDataBaseModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val costReason = itemView.findViewById<TextView>(R.id.cost_reason)
        val costAmount = itemView.findViewById<TextView>(R.id.cost_amount)
        val costDate = itemView.findViewById<TextView>(R.id.cost_date)

        fun bindProduct(costDataBaseModel: CostDataBaseModel, context: Context) {
            // val resourceId = context.resources.getIdentifier(product.image,"drawable",context.packageName)
            //producImage.setImageResource(resourceId)
            costReason.text = costDataBaseModel.costReason
            costAmount.text = costDataBaseModel.costAmount.toString()
            costDate.text = costDataBaseModel.costDate
            itemView.setOnClickListener {
                itemClick(costDataBaseModel)
            }
            //producTitle.text = product.title
            //productContent.text = product.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cost_layout, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(costList[position], context)
    }

    override fun getItemCount(): Int {
        return costList.count()
    }

    fun updateCostList(dataList: ArrayList<CostDataBaseModel>) {
        costList = dataList
        notifyDataSetChanged()
    }


}