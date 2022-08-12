package com.wrench.notebook.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wrench.notebook.databinding.ActivityAddEmployeeBinding
import com.wrench.notebook.model.Employee
import com.wrench.notebook.viewModel.EmployeeViewModel

class ManipulateEmployeeData : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmployeeBinding
    private lateinit var employeeViewModel: EmployeeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_employee)
        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        var flagUpdateOrInsert = intent.getIntExtra("update", 0)
        if (flagUpdateOrInsert == 1) {
            supportActionBar?.title = "Update Employee Data"
            binding.btnManipulate.text = "Insert"
//            binding.btnManipulate.text = employeeViewModel.manipulateButtonText
            val name = intent.getStringExtra("employeeName").toString()
            val dept = intent.getStringExtra("employeeDept").toString()
            val id = intent.getIntExtra("employeeId", 0)

            binding.etName.setText(name)
            binding.etDept.setText(dept)
            binding.btnManipulate.setOnClickListener {
                updateEmployee(id)
            }
        } else {
//            employeeViewModel.manipulateButtonText = "Insert"
            binding.btnManipulate.text = "Insert"
            supportActionBar?.title = "Insert Employee Data"
            binding.btnManipulate.setOnClickListener {
                insertEmployee()
            }
        }

    }

    /**
     * description: insertEmployee() sets data to variable name,dept validates data using dataValidation
     *              and invokes addEmployee() function from viewModel
     */
    private fun insertEmployee() {
        val name = binding.etName.text.toString()
        val dept = binding.etDept.text.toString()
        if (dataValidation(name, dept)) {
            employeeViewModel.addEmployee(Employee(0, name, dept))
            Toast.makeText(this, employeeViewModel.insertionSuccessMessage, Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else
            Toast.makeText(this, employeeViewModel.insertionErrorMessage, Toast.LENGTH_SHORT).show()
    }

    /**
     * description: updatetEmployee() sets data to variable name,dept validates data using dataValidation
     *              and invokes updateEmployee() function from viewModel
     */
    private fun updateEmployee(id: Int) {
        val name = binding.etName.text.toString()
        val dept = binding.etDept.text.toString()
        if (dataValidation(name, dept)) {
            employeeViewModel.updateEmployee(Employee(id, name, dept))
            Toast.makeText(this, employeeViewModel.updationSuccessMessage, Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else
            Toast.makeText(this, employeeViewModel.updationErrorMessage, Toast.LENGTH_SHORT).show()
    }

    /**
     * description: dataValidation() checks whether the fields are empty or not and does basic validation.
     * @param name: Takes in String
     * @param dept: Takes in string
     * @return returns Boolean value as result
     */
    private fun dataValidation(name: String, dept: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(dept) && (name.length > 20) && (dept.length > 5))
    }
}