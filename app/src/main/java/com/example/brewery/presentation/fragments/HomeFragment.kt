package com.example.brewery.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.brewery.R
import com.example.brewery.common.ResponseState
import com.example.brewery.databinding.FragmentHomeBinding
import com.example.brewery.domain.model.Brewery
import com.example.brewery.domain.model.BreweryItem
import com.example.brewery.presentation.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewModelFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private lateinit var breweryAdapter: HomeAdapter

    private var shouldUpdateList = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        viewModel.setLoadingState()
        configureObserver()
        return binding.root
    }


    private fun configureObserver() {
        viewModel.breweryResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.SUCCESS<*> -> {
                    binding.apply {
                        renderList(it.response as Brewery)
                    }

                }
                is ResponseState.ERROR -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvBreweryError.visibility = View.VISIBLE
                        tvBreweryError.text = "Error"
                    }
                }
                is ResponseState.Loading -> {
                    viewModel.getBreweryList()
                }
            }

        }
    }

    private fun renderList(response: Brewery) {
        binding.apply {
            pbLoading.visibility = View.GONE
            rvBreweryList.apply {
                // Setting the adapter once
                if (!shouldUpdateList) {
                    breweryAdapter = HomeAdapter(openBreweryDetails = ::openBreweryDetails)
                    adapter = breweryAdapter
                }
                breweryAdapter.setPeopleList(response, shouldUpdateList)

            }

        }
    }

    private fun openBreweryDetails(node: BreweryItem) {
        // viewModel.setColleagueDetails(node)
        shouldUpdateList = false
        findNavController().navigate(
            R.id.action_navigation_home_to_breweryDetailsFragment,
            bundleOf(
                Pair("name", node.name),
                Pair("type",node.breweryType),
                Pair("street", node.street),
                Pair("city", node.city),
                Pair("state", node.state),
                Pair("country", node.country) ,
                Pair("postCode", node.postalCode)

        )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}