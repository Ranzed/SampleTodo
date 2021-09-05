package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.presentation.viewmodel.TodoTaskViewModel
import java.util.*

class TodoTaskDetailFragment : Fragment(R.layout.detail_fragment), View.OnClickListener {

    companion object Key {
        const val id_key = "todo_key"
    }

    private val title: EditText by lazy { initTitle() }
    private val description: EditText by lazy { initDescription() }
    private val dateTime: TextView by lazy { initDateTime() }
    private val isDone: CheckBox by lazy { initCheckBox() }
    private val buttonSave: Button by lazy { initSaveButton() }
    private val buttonDelete: Button by lazy { initDeleteButton() }

    private lateinit var vm: TodoTaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this).get(TodoTaskViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)

        vm.title.observe(viewLifecycleOwner, { t -> setTextValue(t, title) })
        vm.description.observe(viewLifecycleOwner, { t -> setTextValue(t, description) })
        vm.datetime.observe(viewLifecycleOwner, { t -> setTextValue(t, dateTime) })
        vm.isDone.observe(
            viewLifecycleOwner,
            { t -> if (isDone.isChecked != t) isDone.isChecked = t })
        vm.canSave.observe(viewLifecycleOwner, { t -> buttonSave.isEnabled = t })
        vm.canDelete.observe(
            viewLifecycleOwner,
            { t -> buttonDelete.visibility = if (t) View.VISIBLE else View.GONE })
        vm.load(arguments?.getInt(id_key) ?: 0)
    }

    private fun setTextValue(s: String?, t: TextView) {
        val newText = s ?: ""
        if (!newText.equals(t.text.toString()))
            t.text = newText
    }


    private fun initTitle(): EditText {
        val e = requireView().findViewById<EditText>(R.id.task_title)
        e.doAfterTextChanged { et ->
            val s = et.toString()
            if (!s.equals(vm.title.value))
                vm.title.value = s
        }
        return e
    }

    private fun initDescription(): EditText {
        val e = requireView().findViewById<EditText>(R.id.task_description)
        e.doAfterTextChanged { et ->
            val s = et.toString()
            if (!s.equals(vm.description.value))
                vm.description.value = s
        }
        return e
    }

    private fun initDateTime(): TextView {
        val t = requireView().findViewById<TextView>(R.id.task_datetime)
        t.setOnClickListener(this)
        return t
    }

    private fun initCheckBox(): CheckBox {
        val ch = requireView().findViewById<CheckBox>(R.id.task_is_done)
        ch.setOnCheckedChangeListener { _, isChecked -> vm.isDone.value = isChecked }
        return ch
    }

    private fun initSaveButton(): Button {
        val b = requireView().findViewById<Button>(R.id.btn_save)
        b.setOnClickListener(this)
        return b
    }

    private fun initDeleteButton(): Button {
        val b = requireView().findViewById<Button>(R.id.btn_delete)
        b.setOnClickListener(this)
        return b
    }

    private fun chooseDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.title_date_dialog))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { vm.setDateTime(Date(it)) }
        datePicker.addOnNegativeButtonClickListener { vm.setDateTime(Date(0)) }
        datePicker.show(childFragmentManager, this.javaClass.name)
    }

    override fun onClick(v: View?) {
        if (v == null)
            return

        when (v.id) {
            R.id.btn_save -> vm.clickSave()
            R.id.btn_delete -> vm.clickDelete()
            R.id.task_datetime -> chooseDate()
        }
    }
}