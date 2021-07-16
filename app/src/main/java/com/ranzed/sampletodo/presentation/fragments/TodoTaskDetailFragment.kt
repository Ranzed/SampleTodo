package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.presentation.viewmodel.TodoTaskViewModel

class TodoTaskDetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var vm : TodoTaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(requireActivity()).get(TodoTaskViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)
        // observe
    }

    override fun onResume() {
        super.onResume()
        vm.load(arguments?.getInt("todo_key") ?: 0) // load on every resume
    }
}