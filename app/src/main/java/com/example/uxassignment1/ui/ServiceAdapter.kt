package com.example.uxassignment1.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.example.uxassignment1.databinding.ServiceItemBinding

class ServiceAdapter(private val services: List<Service>, private val selectedService: (Service) -> Unit): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(binding: ServiceItemBinding): RecyclerView.ViewHolder(binding.root) {
        val tvServiceName: TextView = binding.tvServiceName
        val tvServiceInfo: TextView = binding.tvServiceInfo
        val rbisChecked: RadioButton = binding.rbIsChecked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ServiceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentItem = services[position]
        holder.tvServiceName.text = currentItem.name
        holder.tvServiceInfo.text = currentItem.info
        holder.rbisChecked.isChecked = currentItem.isChecked

        holder.rbisChecked.setOnClickListener {
            currentItem.isChecked = true
            selectedService(currentItem)
            notifyDataSetChanged()
        }
    }
}