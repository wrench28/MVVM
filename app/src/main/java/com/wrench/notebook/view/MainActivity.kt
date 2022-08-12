package com.wrench.notebook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wrench.notebook.adapter.EmployeeAdapter
import com.wrench.notebook.databinding.ActivityMainBinding
import com.wrench.notebook.model.Employee
import com.wrench.notebook.viewModel.EmployeeViewModel

class MainActivity : AppCompatActivity(), EmployeeAdapter.ClickListner {
    private lateinit var binding: ActivityMainBinding
    private lateinit var employeeViewModel: EmployeeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        val adapter = EmployeeAdapter(this@MainActivity)
        binding.rvEmployeeData.adapter = adapter
        binding.rvEmployeeData.layoutManager = LinearLayoutManager(this)
        employeeViewModel.readAllEmployeeData.observe(this, Observer { employee ->
            adapter.setData(employee)
        })
        binding.addEmployee.setOnClickListener {
            startActivity(Intent(this, ManipulateEmployeeData::class.java))
        }
    }

    /**
     * Description: update() is defined here from adapter interface class. Starts Manipulate activity
     * @param employee: Takes in Employee type data and passes the value as extra in intent
     */
    override fun update(employee: Employee) {
        val intent = Intent(this, ManipulateEmployeeData::class.java)
        intent.putExtra("update", 1)
        intent.putExtra("employeeName", employee.employeeName)
        intent.putExtra("employeeDept", employee.employeeDept)
        intent.putExtra("employeeId", employee.employeeId)
        startActivity(intent)
    }

    /**
     * description: delete() from adapter class is defined here. deleteEmployee(employee) function is called here.
     * @param employee: Takes in Employee type data and passed to deleteEmployee() function
     */
    override fun delete(employee: Employee) {
        employeeViewModel.deleteEmployee(employee)
    }
}