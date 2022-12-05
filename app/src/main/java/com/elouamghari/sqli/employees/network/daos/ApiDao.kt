package com.elouamghari.sqli.employees.network.daos

import com.elouamghari.sqli.employees.network.responses.EmployeesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDao {

    @GET("users")
    fun getEmployees(
        @Query("page") page : Int = 1
    ) : Observable<EmployeesResponse>

}