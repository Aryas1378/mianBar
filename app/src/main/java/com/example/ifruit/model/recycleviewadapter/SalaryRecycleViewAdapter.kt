package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.recycleviewmodel.CostDataBaseModel
import com.example.ifruit.model.recycleviewmodel.PhoneContactDataBaseModel
import com.example.ifruit.model.recycleviewmodel.SalaryDataBaseModel

class SalaryRecycleViewAdapter(val context: Context, var salaryList: ArrayList<SalaryDataBaseModel>,val itemClick : (SalaryDataBaseModel) ->Unit) : RecyclerView.Adapter<SalaryRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View,val itemClick : (SalaryDataBaseModel) ->Unit) : RecyclerView.ViewHolder(itemView) {
        val salaryName = itemView.findViewById<TextView>(R.id.salaryes_name)
        val salary = itemView.findViewById<TextView>(R.id.salary_salary)
        val salaryPhoneNumber = itemView.findViewById<TextView>(R.id.salary_phone_number)

        fun bindProduct(salaryDataBaseModel: SalaryDataBaseModel, context: Context) {

            salaryName.text=salaryDataBaseModel.name.toString()
            salary.text=salaryDataBaseModel.salary.toString()
            salaryPhoneNumber.text=salaryDataBaseModel.phoneNumber.toString()

            itemView.setOnClickListener {
                itemClick(salaryDataBaseModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.salary_layout, parent, false)
        return ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(salaryList[position], context)
    }

    override fun getItemCount(): Int {
        return salaryList.count()
    }
    fun updateSalaryList(dataList: ArrayList<SalaryDataBaseModel>) {
        salaryList = dataList
        notifyDataSetChanged()
    }


}