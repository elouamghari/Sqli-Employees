package com.elouamghari.sqli.employees.network.clients

import com.elouamghari.sqli.employees.network.responses.EmployeesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDaoTest {
    @GET("users")
    fun getEmployees(
        @Query("page") page : Int = 1
    ) : Call<EmployeesResponse>
}