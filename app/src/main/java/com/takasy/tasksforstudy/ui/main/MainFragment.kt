package com.takasy.tasksforstudy.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.takasy.tasksforstudy.databinding.ContentHolderBinding
import com.takasy.tasksforstudy.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchContents().observe(viewLifecycleOwner) { list ->
            setupRecyclerView(list)
            setupFlow(list)
        }
    }

    private fun setupRecyclerView(list: List<ContentsAdapter.Content>) {
        binding.recyclerView.adapter = ContentsAdapter(list)
        binding.recyclerView.layoutManager = FlexboxLayoutManager(requireContext()).also {
            it.flexDirection = FlexDirection.ROW
            it.justifyContent = JustifyContent.CENTER
        }
    }

    private fun setupFlow(list: List<ContentsAdapter.Content>) {
        val contentIds = mutableListOf<Int>()
        list.forEach { content ->
            val holder = ContentHolderBinding.inflate(LayoutInflater.from(requireContext()))
            holder.name.text = content.name
            holder.image.setImageResource(content.imageColor.imageId)

            holder.root.id = View.generateViewId()
            contentIds.add(holder.root.id)
            binding.root.addView(holder.root)
        }
        binding.flowContainer.referencedIds = contentIds.toIntArray()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}