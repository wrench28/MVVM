package com.wrench.notebook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employee")
data class Employee (

    @PrimaryKey(autoGenerate = true)
     val employeeId: Int,

    @ColumnInfo(name = "name")
     val employeeName: String,

    @ColumnInfo(name = "dept")
     val employeeDept: String

)


