package com.example.uxassignment1

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView

class ServiceAdapter(private val services: List<Service>, private val selectedService: (Service) -> Unit): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvServiceName: TextView = itemView.findViewById(R.id.tvServiceName)
//        val tvServiceInfo: TextView = itemView.findViewById(R.id.tvServicInfo)
        val rbisChecked: RadioButton = itemView.findViewById(R.id.rbIsChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentItem = services[position]
        holder.tvServiceName.text = currentItem.name
//        holder.tvServiceInfo.text = currentItem.info
        holder.rbisChecked.isChecked = currentItem.isChecked

        holder.rbisChecked.setOnClickListener {
            currentItem.isChecked = true
            selectedService(currentItem)

            notifyDataSetChanged()
        }
    }
}