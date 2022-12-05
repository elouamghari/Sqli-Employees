package com.elouamghari.sqli.employees.ui.employees.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elouamghari.sqli.employees.R
import com.elouamghari.sqli.employees.databinding.ListItemEmployeeBinding
import com.elouamghari.sqli.employees.network.models.Employee

class EmployeesAdapter : ListAdapter<Employee?, RecyclerView.ViewHolder>(EmployeeItemComparator()) {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding: ListItemEmployeeBinding = ListItemEmployeeBinding.bind(itemView)

        fun bind(employee: Employee){
            binding.employee = employee
        }
    }

    inner class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class EmployeeItemComparator : DiffUtil.ItemCallback<Employee?>(){
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if(viewType == VIEW_TYPE_ITEM){
            EmployeeViewHolder(inflater.inflate(R.layout.list_item_employee, parent, false))
        } else{
            LoadingViewHolder(inflater.inflate(R.layout.list_item_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EmployeeViewHolder){
            holder.bind(getItem(position)!!)
        }
    }

}