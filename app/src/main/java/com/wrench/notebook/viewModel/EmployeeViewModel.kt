package com.wrench.notebook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wrench.notebook.model.Employee
import com.wrench.notebook.util.EmployeeDatabase
import com.wrench.notebook.model.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application):AndroidViewModel(application) {
    val readAllEmployeeData: LiveData<List<Employee>>
    var manipulateButtonText: String = "Update"
    private val repository: EmployeeRepository
    val insertionErrorMessage = "Data Invalid"
    val insertionSuccessMessage = "Data Inserted Successfully"
    val updationSuccessMessage = "Data Updated Successfully"
    val updationErrorMessage = "Data updation Unsuccessful"
    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        readAllEmployeeData = repository.readAllEmployeeData
    }

    /**
     * description: addEmployee() calls in addEmployee() function from repository via coroutine scope.
     * @param employee: Employee type data is passed to add employee to room db
     */
    fun addEmployee(employee: Employee)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employee)
        }
    }
    /**
     * description: updateEmployee() calls in updateEmployee() function from repository via coroutine scope.
     * @param employee: Employee type data is passed to update employee to room db
     */
    fun updateEmployee(employee: Employee)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEmployee(employee)
        }
    }
    /**
     * description: deleteEmployee() calls in deleteEmployee() function from repository via coroutine scope.
     * @param employee: Employee type data is passed to delete employee to room db
     */
    fun deleteEmployee(employee: Employee)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }
}