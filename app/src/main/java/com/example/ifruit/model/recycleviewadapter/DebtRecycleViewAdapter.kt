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

class DebtRecycleViewAdapter(val context: Context, var debtList: ArrayList<DebtDataBaseModel>,val itemClick : (DebtDataBaseModel) ->Unit) :
    RecyclerView.Adapter<DebtRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View,val itemClick : (DebtDataBaseModel) ->Unit) : RecyclerView.ViewHolder(itemView) {
        val DebtorName = itemView.findViewById<TextView>(R.id.debt_name)
        val DebtorphoneNumber = itemView.findViewById<TextView>(R.id.debt_phone_number)
        val debtAmount = itemView.findViewById<TextView>(R.id.debt_amount)

        fun bindProduct(debtDataBaseModel: DebtDataBaseModel, context: Context) {

            DebtorName.text = debtDataBaseModel.name
            DebtorphoneNumber.text = debtDataBaseModel.phoneNumber.toString()
            debtAmount.text = debtDataBaseModel.debtAmount.toString()
            itemView.setOnClickListener {
                itemClick(debtDataBaseModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.debt_layout, parent, false)
        return ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(debtList[position], context)

    }

    override fun getItemCount(): Int {
        return debtList.count()

    }

    fun updateDebtList(dataList: ArrayList<DebtDataBaseModel>) {
        debtList = dataList
        notifyDataSetChanged()
    }

}