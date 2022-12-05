package com.elouamghari.sqli.employees.network.responses

import com.elouamghari.sqli.employees.network.models.Employee
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val employees: List<Employee>,
)