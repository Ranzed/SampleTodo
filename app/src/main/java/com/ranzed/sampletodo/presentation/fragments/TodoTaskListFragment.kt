package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
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

    private val recycler : RecyclerView by lazy { initRecycler() }
    private val adapter = TodoListAdapter()
    private val loadingBox : ViewGroup by lazy { requireView().findViewById(R.id.loading_container) }
    private val button : Button by lazy { initCreateButton() }

    private lateinit var vm : TodoListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)

        vm.IsLoading.observe(viewLifecycleOwner, { isLoading ->
            loadingBox.visibility = if (isLoading) View.VISIBLE else View.GONE
            recycler.visibility = if (isLoading) View.GONE else View.VISIBLE
            button.isEnabled = !isLoading
        })
        vm.TodoTasks.observe(viewLifecycleOwner, { list -> setupList(list) })
    }

    override fun onResume() {
        super.onResume()
        vm.load() // load on every resume
    }

    private fun initRecycler() : RecyclerView  {
        var r = requireView().findViewById<RecyclerView>(R.id.recycler);
        r.layoutManager = LinearLayoutManager(requireContext())
        r.adapter = adapter
        return r
    }

    private fun initCreateButton() : Button {
        var b = requireView().findViewById<Button>(R.id.btn_create_new);
        b.setOnClickListener(this)
        return b
    }

    private fun setupList(todoTasks : List<TodoTask>) {
        adapter.items.clear()
        adapter.items.addAll(todoTasks)
        adapter.notifyDataSetChanged()
        // TODO use diffUtils
    }

    override fun onClick(v: View?) {
        if (v == null)
            return
        when (v.id) {
            R.id.btn_create_new -> vm.clickCreateBtn()
        }

    }
}