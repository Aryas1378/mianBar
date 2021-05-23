package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.recycleviewmodel.ContractDataBaseModel
import com.example.ifruit.model.recycleviewmodel.CostDataBaseModel
import com.example.ifruit.model.recycleviewmodel.EmployeeManagementDataBaseModel

class EmployeeRecycleViewAdapter(
    val context: Context,
    var employeeList: ArrayList<EmployeeManagementDataBaseModel>
) : RecyclerView.Adapter<EmployeeRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val employeeName = itemView.findViewById<TextView>(R.id.employee_name)
        val employeePhoneNumber = itemView.findViewById<TextView>(R.id.employee_phone_number)
        val dateOfEmployee = itemView.findViewById<TextView>(R.id.date_of_employee)
        val salary = itemView.findViewById<TextView>(R.id.employee_salary)
        val jobTitle = itemView.findViewById<TextView>(R.id.job_title)


        fun bindProduct(
            employeeManagementDataBaseModel: EmployeeManagementDataBaseModel,
            context: Context
        ) {

            employeeName.text = employeeManagementDataBaseModel.firstName
            employeePhoneNumber.text = employeeManagementDataBaseModel.phoneNumber.toString()
            dateOfEmployee.text = employeeManagementDataBaseModel.dateOfEmployee.toString()
            salary.text = employeeManagementDataBaseModel.salary.toString()
            jobTitle.text = employeeManagementDataBaseModel.jobTitle

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.employee_managment_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(employeeList[position],context)
    }

    override fun getItemCount(): Int {
        return employeeList.count()
    }

    fun updateEmployeeList(dataList : ArrayList<EmployeeManagementDataBaseModel>){
        employeeList = dataList
        notifyDataSetChanged()
    }

}