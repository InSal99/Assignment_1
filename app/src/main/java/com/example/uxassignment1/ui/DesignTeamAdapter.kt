package com.example.uxassignment1.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uxassignment1.data.local.entity.DesignTeam
import com.example.uxassignment1.databinding.ItemDesignTeamBinding
import kotlinx.coroutines.withContext

class DesignTeamAdapter: RecyclerView.Adapter<DesignTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDesignTeamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemDesignTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DesignTeam) {
            binding.run {
                tvName.text = item.name
                tvDivision.text = item.division

                binding.root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }

                // Set the delete button click listener
                btnDelete.setOnClickListener {
                    onDeleteClickListener?.invoke(item)
                }

                // Set the update button click listener
                btnEdit.setOnClickListener {
                    onUpdateClickListener?.invoke(item) // Invoke the update listener
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DesignTeam>() {
        override fun areItemsTheSame(
            oldExampleModel: DesignTeam, newExampleModel: DesignTeam
        ): Boolean {
            return oldExampleModel.id == newExampleModel.id
        }

        override fun areContentsTheSame(
            oldExampleModel: DesignTeam, newExampleModel: DesignTeam
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((DesignTeam) -> Unit)? = null
    private var onDeleteClickListener: ((DesignTeam) -> Unit)? = null
    private var onUpdateClickListener: ((DesignTeam) -> Unit)? = null

    fun setOnItemClickListener(listener: (DesignTeam) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (DesignTeam) -> Unit) {
        onDeleteClickListener = listener
    }

    fun setOnUpdateClickListener(listener: (DesignTeam) -> Unit) {
        onUpdateClickListener = listener
    }

}