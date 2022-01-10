package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.presentation.view.TodoListAdapter
import com.ranzed.sampletodo.presentation.viewmodel.TodoListViewModel

class TodoTaskListFragment : Fragment(R.layout.list_fragment), View.OnClickListener {

    private var recycler: RecyclerView? = null
    private var emptyStub: View? = null
    private val adapter = TodoListAdapter { vm.clickTodoItem(it) }
    private var loadingContainer: ViewGroup? = null
    private var listContainer: ViewGroup? = null
    private var button: Button? = null

    private lateinit var vm: TodoListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(javaClass.name, "onViewCreated on thread ${Thread.currentThread().name}")
        with(requireView()) {
            emptyStub = findViewById(R.id.empty_stub)
            loadingContainer = findViewById(R.id.loading_container)
            listContainer = findViewById(R.id.list_container)
            recycler = initRecycler(findViewById(R.id.recycler))
            button = findViewById<Button>(R.id.btn_create_new).apply {
                setOnClickListener(this@TodoTaskListFragment)
            }
        }

        vm = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)
        vm.isLoading.observe(viewLifecycleOwner, { setupContainersVisibility(it) })
        vm.isEmpty.observe(viewLifecycleOwner, { setupListVisibility(it) })
        vm.todoTasks.observe(viewLifecycleOwner, { setupList(it) })
    }

    override fun onResume() {
        super.onResume()
        vm.load() // load on every resume
    }

    private fun initRecycler(r: RecyclerView): RecyclerView {
        r.layoutManager = LinearLayoutManager(requireContext())
        r.adapter = adapter
        return r
    }

    private fun setupContainersVisibility(isLoading: Boolean) {
        loadingContainer?.visibility = if (isLoading) View.VISIBLE else View.GONE
        listContainer?.visibility = if (isLoading) View.GONE else View.VISIBLE
        button?.isEnabled = !isLoading
    }

    private fun setupListVisibility(isEmpty: Boolean) {
        recycler?.visibility = if (isEmpty) View.GONE else View.VISIBLE
        emptyStub?.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun setupList(todoTasks: List<TodoTask>) {
        adapter.items.clear()
        adapter.items.addAll(todoTasks)
        adapter.notifyDataSetChanged()
        // TODO use diffUtils
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create_new -> vm.clickCreateBtn()
        }
    }
}