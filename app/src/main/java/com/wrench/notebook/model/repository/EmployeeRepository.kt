package com.wrench.notebook.model.repository

import androidx.lifecycle.LiveData
import com.wrench.notebook.dao.EmployeeDao
import com.wrench.notebook.model.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    /**
     * description : readAllEmployeeData recieves latest set of data from the room DB will be of Livedata type.
     *               calls readEmployees() function from Dao class and assigns to readAllEmployeeData
     */
    val readAllEmployeeData: LiveData<List<Employee>> = employeeDao.readEmployees()

    /**
     * description : addEmployee function calls insert functions of Dao class and performs insert operation over room
     * @param employee : takes in Employee type data as parameter and passes the data to insert function's argument.
     */
    suspend fun addEmployee(employee: Employee)
    {
        employeeDao.insertEmployee(employee)
    }

    /**
     * description : updateEmployee function calls update functions of Dao class and performs update operation over room
     * @param employee : takes in Employee type data as parameter and passes the data to insert function's argument.
     */
    suspend fun updateEmployee(employee: Employee)
    {
        employeeDao.updateEmployee(employee)
    }

    /**
     * description : deleteEmployee function calls delete functions of Dao class and performs delete operation over room
     * @param employee : takes in Employee type data as parameter and passes the data to delete function's argument.
     */
    suspend fun deleteEmployee(employee: Employee)
    {
        employeeDao.deleteEmployee(employee)
    }
}