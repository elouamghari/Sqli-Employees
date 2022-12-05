package com.elouamghari.sqli.employees.network.clients

import com.elouamghari.sqli.employees.network.constants.ApiConstants
import org.junit.Assert.*
import org.junit.Test

class ApiClientTest{

    @Test
    fun testApiClientInstance(){
        val instance = ApiClient.apiClient
        assert(instance.baseUrl().url().toString() == ApiConstants.API_BASE_URL)
    }

    @Test
    fun testEmployeesApiCall(){
        val instance = ApiClient.apiClient
        val dao = instance.create(ApiDaoTest::class.java)
        // Execute the API Call
        val response = dao.getEmployees().execute()
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }

    @Test
    fun testEmployeesApi(){
        val dao = ApiClient.apiDao
        val response = dao.getEmployees(page = 1).blockingSingle()
        assert(response.employees.isNotEmpty())
    }
}