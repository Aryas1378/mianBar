package com.example.ifruit.model.recycleviewadapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.model.*

class InvoiceAdapter(context: Context, cursor: Cursor) :RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>(){
    private val mContext: Context? = context
    private var mCursor: Cursor? = cursor

    class InvoiceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fruitBasket:ImageView=itemView.findViewById(R.id.fruit_basket_image)
        val fruitName:TextView=itemView.findViewById(R.id.name_text)
        val fruitAmount:TextView=itemView.findViewById(R.id.amount_text)
        val fruitPrice:TextView=itemView.findViewById(R.id.price_text)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        val itemView=LayoutInflater.from(mContext).inflate(R.layout.layout_fruit_item,
            parent, false)

        return InvoiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        System.out.println("THis is name")
        if (!mCursor!!.moveToPosition(position))
            return

        var name:String=mCursor!!.getString(mCursor!!.getColumnIndex(KEY_INVOICE_FRUIT_NAME))
        var amount:Float=mCursor!!.getFloat(mCursor!!.getColumnIndex(KEY_INVOICE_FRUIT_AMOUNT))
        var price:Int=mCursor!!.getInt(mCursor!!.getColumnIndex(KEY_INVOICE_SUM))
        var id:Int=mCursor!!.getInt(mCursor!!.getColumnIndex(KEY_INVOICE_ID))

        System.out.println("THis is name" +name)
        System.out.println("THis is name" +amount)
        System.out.println("THis is name" +price)


        holder.fruitName.text="نام میوه: "+name
        holder.fruitAmount.text="وزن میوه: "+amount.toString() +" کیلوگرم"
        holder.fruitPrice.text="قیمت: "+price.toString() +" تومان"
        holder.itemView.tag=id

    }

    override fun getItemCount(): Int {
        if (mCursor != null) {
            return mCursor!!.count
        }
        return 0
    }

    fun swapCursor(newCursor: Cursor) {
        if (mCursor != null) {
            mCursor!!.close()
        }
        mCursor = newCursor
        if (newCursor != null) {
            notifyDataSetChanged()
        }
    }
}