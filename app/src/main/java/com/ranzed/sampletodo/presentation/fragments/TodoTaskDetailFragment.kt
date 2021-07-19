package com.ranzed.sampletodo.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ranzed.sampletodo.App
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.presentation.viewmodel.TodoTaskViewModel

class TodoTaskDetailFragment : Fragment(R.layout.detail_fragment), View.OnClickListener {

    private val title : EditText by lazy { initTitle() }
    private val description : EditText by lazy { initDescription() }
    private val dateTime : TextView by lazy { initDateTime() }
    private val buttonSave : Button by lazy { initSaveButton() }
    private val buttonDelete : Button by lazy { initDeleteButton() }

    private lateinit var vm : TodoTaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(requireActivity()).get(TodoTaskViewModel::class.java)
        (requireContext().applicationContext as App).appComponent.inject(vm)
        initTitle()
        initDescription()
        vm.Title.observe(viewLifecycleOwner, { t ->  })
        vm.Description.observe(viewLifecycleOwner, { t -> })
        vm.Datetime.observe(viewLifecycleOwner, { t -> dateTime.text = t })
        vm.CanSave.observe(viewLifecycleOwner, { t -> buttonSave.isEnabled = t })
        vm.CanDelete.observe(viewLifecycleOwner, { t -> if (t) View.VISIBLE else View.GONE })
    }

    private fun initTitle() : EditText {
        val e = requireView().findViewById<EditText>(R.id.task_title)
        e.doAfterTextChanged { et ->
            val s = et.toString()
            if (!s.equals(vm.Title.value))
                vm.Title.value = s
        }
//        e.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // TODO("Not yet implemented")
//            }
//
//            override fun afterTextChanged(e: Editable?) {
//                val s = e.toString()
//                if (!s.equals(vm.Title.value))
//                    vm.Title.value = s
//            }
//        })
        return e
    }

    private fun initDescription() : EditText {
        val e = requireView().findViewById<EditText>(R.id.task_description)
        e.doAfterTextChanged { et ->
            val s = et.toString()
            if (!s.equals(vm.Description.value))
                vm.Description.value = s}
        return e
    }

    private fun initDateTime() : TextView {
        val t = requireView().findViewById<TextView>(R.id.task_datetime)
        t.setOnClickListener { chooseDate() }
        return t
    }

    private fun initSaveButton() : Button {
        val b = requireView().findViewById<Button>(R.id.btn_save);
        b.setOnClickListener(this)
        return b
    }

    private fun initDeleteButton() : Button {
        val b = requireView().findViewById<Button>(R.id.btn_delete);
        b.setOnClickListener(this)
        return b
    }

    private fun chooseDate() {
        // todo datepicker
        // ondatecheck = vm.setDate()
    }

    override fun onResume() {
        super.onResume()
        vm.load(arguments?.getInt("todo_key") ?: 0) // load on every resume
    }

    override fun onClick(v: View?) {
        if (v == null)
            return

        when (v.id) {
            R.id.btn_save -> vm.clickSave()
            R.id.btn_delete -> vm.clickDelete()
        }
    }


}