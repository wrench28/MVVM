package com.wrench.notebook.model.repository

import androidx.lifecycle.LiveData
import com.wrench.notebook.dao.EmployeeDao
import com.wrench.notebook.model.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    val readAllEmployeeData: LiveData<List<Employee>> = employeeDao.readEmployees()

    suspend fun addEmployee(employee: Employee)
    {
        employeeDao.insertEmployee(employee)
    }
    suspend fun updateEmployee(employee: Employee)
    {
        employeeDao.updateEmployee(employee)
    }
    suspend fun deleteEmployee(employee: Employee)
    {
        employeeDao.deleteEmployee(employee)
    }
}