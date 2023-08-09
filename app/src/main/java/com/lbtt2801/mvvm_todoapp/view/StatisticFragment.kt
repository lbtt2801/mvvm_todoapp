package com.lbtt2801.mvvm_todoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lbtt2801.mvvm_todoapp.databinding.FragmentStatisticBinding
import com.lbtt2801.mvvm_todoapp.viewmodel.StatisticViewModel

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val statisticViewModel by lazy {
        ViewModelProvider(this)[StatisticViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatisticBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        statisticViewModel.action.observe(viewLifecycleOwner) {
            binding.tvAction.text = "Active Task: $it %"
        }
        statisticViewModel.getActionTask()

        statisticViewModel.complete.observe(viewLifecycleOwner) {
            binding.tvCompleted.text = "Completed Task: $it %"
        }
        statisticViewModel.getCompleteTask()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}