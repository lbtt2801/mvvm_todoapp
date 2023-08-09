package com.lbtt2801.mvvm_todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.mvvm_todoapp.adapter.TodoAdapter
import com.lbtt2801.mvvm_todoapp.databinding.FragmentHomeBinding
import com.lbtt2801.mvvm_todoapp.databinding.ListTaskBinding
import com.lbtt2801.mvvm_todoapp.model.AppToDoTasks
import com.lbtt2801.mvvm_todoapp.model.Task
import com.lbtt2801.mvvm_todoapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lstTaskLiveData.observe(viewLifecycleOwner) {
            initAdapter(it)
        }

        val activity: MainActivity? = activity as MainActivity?
        val filterType: String = activity!!.filterData()

        viewModel.getDataFilter(filterType)

        if (viewModel.lstTasks.isEmpty())
            binding.tvTask.text = ""
        else {
            binding.tvEmpty.text = ""
            binding.imgEmpty.setImageDrawable(null)
        }

        binding.taskRecycler.setOnClickListener {
            AppToDoTasks.todoTasks = viewModel.lstTasks.toMutableList()
        }
    }

    private fun initAdapter(lst: List<Task>) {
        val context = activity?.applicationContext ?: return

        val todoAdapter = TodoAdapter(lst)
        binding.taskRecycler.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}