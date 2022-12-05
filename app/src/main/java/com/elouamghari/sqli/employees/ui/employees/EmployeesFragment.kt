package com.elouamghari.sqli.employees.ui.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elouamghari.sqli.employees.core.extensions.loadMore
import com.elouamghari.sqli.employees.databinding.FragmentEmployeesBinding
import com.elouamghari.sqli.employees.network.models.Employee
import com.elouamghari.sqli.employees.ui.employees.adapters.EmployeesAdapter

class EmployeesFragment : Fragment() {

    private lateinit var binding: FragmentEmployeesBinding
    private val adapter = EmployeesAdapter()
    private val viewModel: EmployeesFragmentViewModel by lazy {
        ViewModelProvider(this)[EmployeesFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.employeesRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.employeesRecyclerView.loadMore{
            viewModel.loadEmployees()
        }
        viewModel.employees.observe(viewLifecycleOwner, this::onEmployeesLoaded)
    }

    private fun onEmployeesLoaded(movies: List<Employee>) {
        if (movies.isNotEmpty()){
            val list: MutableList<Employee?> = movies.toMutableList()
            if (!viewModel.isLastPage){
                list.add(null)
            }
            adapter.submitList(list)
        }
    }
}