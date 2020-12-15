package com.sagycorp.castle511.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sagycorp.castle511.R
import com.sagycorp.castle511.data.TrafficData
import com.sagycorp.castle511.ui.home.HomeFragment

class TrafficDataAdapter(private val dataList: List<TrafficData>, private val context: HomeFragment):
    RecyclerView.Adapter<TrafficDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater, parent, context )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trafficData: TrafficData = dataList[position]
        holder.bind(trafficData)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup, context: HomeFragment) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.traffic_list_row, parent, false)),
        View.OnClickListener{

        private var mContext: HomeFragment? = null
        private var mName: TextView? = null
        private var mTrafficData: TrafficData? = null

        init {
            mContext = context
            mName = itemView.findViewById(R.id.rowItem)
            itemView.setOnClickListener(this)
        }

        fun bind(trafficData: TrafficData){
            mTrafficData = trafficData
            if (!trafficData.status.equals("DISPLAYING_MESSAGE")) mName?.setTextColor(Color.parseColor("#808080"))
            else mName?.setTextColor(Color.parseColor("#000000"))
            mName?.text = trafficData.name
        }

        override fun onClick(v: View?) {
                mTrafficData?.let {
                    mContext?.homeToDetailsScreen(v,it)
                }
        }

    }
}