package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.databinding.DetailFragmentBinding
import com.ranzed.sampletodo.presentation.viewmodel.TodoTaskViewModel
import java.util.Date

class TodoTaskDetailFragment : Fragment(), View.OnClickListener {

    private var binding: DetailFragmentBinding? = null
    private lateinit var vm: TodoTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding?.apply {
            taskTitle.doAfterTextChanged { updateTextValue(it.toString(), vm.title) }
            taskDescription.doAfterTextChanged { updateTextValue(it.toString(), vm.description) }
            taskDatetime.setOnClickListener(this@TodoTaskDetailFragment)
            btnSave.setOnClickListener(this@TodoTaskDetailFragment)
            btnDelete.setOnClickListener(this@TodoTaskDetailFragment)
            taskIsDone.setOnCheckedChangeListener { _, isChecked -> vm.isDone.value = isChecked }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(javaClass.name, "onViewCreated on thread " + Thread.currentThread().name)
        vm = ViewModelProvider(this).get(TodoTaskViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)

        binding?.apply {
            vm.title.observe(viewLifecycleOwner, { t -> setTextValue(t, taskTitle) })
            vm.description.observe(viewLifecycleOwner, { t -> setTextValue(t, taskDescription) })
            vm.datetime.observe(viewLifecycleOwner, { t -> setTextValue(t, taskDatetime) })
            vm.isDone.observe(viewLifecycleOwner,
                { t -> if (taskIsDone.isChecked != t) taskIsDone.isChecked = t })
            vm.canSave.observe(viewLifecycleOwner, { t -> btnSave.isEnabled = t })
            vm.canDelete.observe(viewLifecycleOwner, { v -> btnDelete.isVisible = v})
            vm.isLoading.observe(viewLifecycleOwner, { t -> btnSave.isVisible = !t })
        }

        vm.load(arguments?.getInt(KEY_ID) ?: 0)
    }

    private fun setTextValue(s: String?, t: TextView) {
        if (s != t.text.toString())
            t.text = s.orEmpty()
    }

    private fun updateTextValue(value: String, liveData: MutableLiveData<String>) {
        if (!value.equals(liveData.value))
            liveData.value = value
    }

    private fun chooseDate() {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.title_date_dialog))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
            .apply {
                addOnPositiveButtonClickListener { vm.setDateTime(Date(it)) }
                addOnNegativeButtonClickListener { vm.setDateTime(Date(0)) }
                show(this@TodoTaskDetailFragment.childFragmentManager, TAG)
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_save -> vm.clickSave()
            R.id.btn_delete -> vm.clickDelete()
            R.id.task_datetime -> chooseDate()
        }
    }

    companion object {
        private const val TAG = "TodoTaskDetailFragment"
        const val KEY_ID = "todo_key"
    }
}