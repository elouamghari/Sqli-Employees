package com.elouamghari.sqli.employees.ui.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elouamghari.sqli.employees.network.Api
import com.elouamghari.sqli.employees.network.clients.ApiClient
import com.elouamghari.sqli.employees.network.models.Employee
import com.elouamghari.sqli.employees.network.responses.EmployeesResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class EmployeesFragmentViewModel : ViewModel() {

    private var currentPage: Int = 0
    private var totalPages: Int = 1
    private val employeesList: MutableList<Employee> = mutableListOf()
    private val employeesMutable: MutableLiveData<List<Employee>> = MutableLiveData(listOf())

    val isLastPage : Boolean
        get() = currentPage == totalPages
    val employees: LiveData<List<Employee>> by lazy { employeesMutable }

    init {
        loadEmployees()
    }

    fun loadEmployeesOfPage(page: Int){
        Api.call(ApiClient.apiDao.getEmployees(page= page), object : Observer<EmployeesResponse>{
            override fun onNext(response: EmployeesResponse) {
                currentPage = response.page
                totalPages = response.totalPages
                employeesList.addAll(response.employees)
                employeesMutable.value = employeesList
            }

            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }

    fun loadEmployees(){
        if (currentPage < totalPages){
            currentPage++
            loadEmployeesOfPage(currentPage)
        }
    }
}