package com.wrench.notebook.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wrench.notebook.model.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun readEmployees(): LiveData<List<Employee>>

    @Delete
    suspend fun deleteEmployee(employee: Employee)
}