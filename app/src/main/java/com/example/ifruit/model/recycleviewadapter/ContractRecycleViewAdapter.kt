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

class ContractRecycleViewAdapter(
    val context: Context,
    var contractList: ArrayList<ContractDataBaseModel>
) : RecyclerView.Adapter<ContractRecycleViewAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contractTitle = itemView.findViewById<TextView>(R.id.contract_title)
        val name = itemView.findViewById<TextView>(R.id.contract_name)
        val transactionVolume = itemView.findViewById<TextView>(R.id.transaction_volume)
        val nationalCode = itemView.findViewById<TextView>(R.id.national_code)
        val productInfo = itemView.findViewById<TextView>(R.id.contract_product_info)
        val date = itemView.findViewById<TextView>(R.id.contract_date)


        fun bindProduct(contractDataBaseModel: ContractDataBaseModel, context: Context) {

            contractTitle.text = contractDataBaseModel.contractTitle
            name.text = contractDataBaseModel.name
            transactionVolume.text = contractDataBaseModel.transactionVolume.toString()
            nationalCode.text = contractDataBaseModel.nationalCode.toString()
            productInfo.text = contractDataBaseModel.productInformation
            date.text = contractDataBaseModel.date.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contract_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(contractList[position],context)
    }

    override fun getItemCount(): Int {
        return contractList.count()
    }
    fun updateContractList(dataList : ArrayList<ContractDataBaseModel>){
        contractList = dataList
        notifyDataSetChanged()
    }

}