package com.example.android.fetchtakehomeapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.fetchtakehomeapp.R
import com.example.android.fetchtakehomeapp.databinding.FragmentHomeListBinding
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeListFragment : Fragment() {

    lateinit var binding: FragmentHomeListBinding

    private val homeListViewModel: HomeListViewModel by viewModels()

    lateinit var itemListAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeListBinding.inflate(inflater)

        homeListViewModel.getInfo()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        homeListViewModel.getInforForDb()

        homeListViewModel.informationList.observe(viewLifecycleOwner) { item ->
            item.apply { itemListAdapter.differ.submitList(item) }
        }

    }

    private fun setUpRecyclerView() {
        itemListAdapter = ListAdapter()
        binding.recView.apply{
            adapter = itemListAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

}