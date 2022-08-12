package com.wrench.notebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wrench.notebook.databinding.RecyclerViewListItemBinding
import com.wrench.notebook.model.Employee

class EmployeeAdapter(listner:ClickListner):RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    private val cListner:ClickListner
    init {
        this.cListner = listner
    }
    private  var employeeList = emptyList<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = employeeList[position]
        holder.binding.tvEmployeeName.text = currentItem.employeeName
        holder.binding.tvEmployeeDept.text = currentItem.employeeDept
        holder.binding.ivUpdate.setOnClickListener {
            cListner.update(currentItem)
        }
        holder.binding.ivDelete.setOnClickListener {
            cListner.delete(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
    inner class ViewHolder(val binding: RecyclerViewListItemBinding) : RecyclerView.ViewHolder(binding.root)
    interface ClickListner{
        fun update(employee: Employee)
        fun delete(employee: Employee)
    }

    /**
     * description: will observe any change in data and notifies the view.
     * @param employee: will recieve the updated data as list and assigns it.
     */
    fun setData(employee: List<Employee>)
    {
        this.employeeList = employee
        notifyDataSetChanged()
    }
}