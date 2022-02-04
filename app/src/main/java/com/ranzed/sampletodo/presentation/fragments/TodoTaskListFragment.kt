package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.view.isVisible
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.databinding.ListFragmentBinding
import com.ranzed.sampletodo.presentation.view.TodoListAdapter
import com.ranzed.sampletodo.presentation.viewmodel.TodoListViewModel

class TodoTaskListFragment : Fragment(), View.OnClickListener {

    private val adapter = TodoListAdapter { vm.clickTodoItem(it) }
    private var binding: ListFragmentBinding? = null
    private lateinit var vm: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        binding?.apply {
            btnCreateNew.setOnClickListener(this@TodoTaskListFragment)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(javaClass.name, "onViewCreated on thread ${Thread.currentThread().name}")

        vm = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)

        vm.isLoading.observe(viewLifecycleOwner, { setupContainersVisibility(it) })
        vm.isEmpty.observe(viewLifecycleOwner, { setupListVisibility(it) })
        vm.todoTasks.observe(viewLifecycleOwner, { adapter.setupList(it) })
    }

    override fun onResume() {
        super.onResume()
        vm.load() // load on every resume
    }

    private fun setupContainersVisibility(isLoading: Boolean) {
        binding?.apply {
            loadingContainer.isVisible = isLoading
            listContainer.isVisible = !isLoading
            btnCreateNew.isEnabled = !isLoading
        }
    }

    private fun setupListVisibility(isEmpty: Boolean) {
        binding?.apply {
            recycler.isVisible = !isEmpty
            emptyStub.isVisible = isEmpty
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_create_new -> vm.clickCreateBtn()
        }
    }
}