package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.recycleviewmodel.CostDataBaseModel
import com.example.ifruit.model.recycleviewmodel.FruitDataBaseModel
import com.example.ifruit.model.recycleviewmodel.PhoneContactDataBaseModel

class PhoneContactRecycleViewAdapter(
    val context: Context,
    var phoneNumberGroup: ArrayList<PhoneContactDataBaseModel>
) : RecyclerView.Adapter<PhoneContactRecycleViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName = itemView.findViewById<TextView>(R.id.phone_contact_name)
        val contactPhoneNumber = itemView.findViewById<TextView>(R.id.phone_contact_number)
        val contactTitle = itemView.findViewById<TextView>(R.id.contact_title)

        fun bindProduct(phoneContactDataBaseModel: PhoneContactDataBaseModel, context: Context) {

            contactName.text = phoneContactDataBaseModel.firstName
            contactPhoneNumber.text = phoneContactDataBaseModel.phoneNumber.toString()
            contactTitle.text = phoneContactDataBaseModel.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.phone_contact_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(phoneNumberGroup[position], context)
    }

    override fun getItemCount(): Int {
        return phoneNumberGroup.count()
    }

    fun updateFruitList(dataList: ArrayList<PhoneContactDataBaseModel>) {
        phoneNumberGroup = dataList
        notifyDataSetChanged()
    }

}