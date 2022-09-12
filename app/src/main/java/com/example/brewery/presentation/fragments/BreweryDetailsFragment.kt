package com.example.brewery.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.brewery.databinding.FragmentBreweryDetailsBinding
import com.example.brewery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreweryDetailsFragment : ViewModelFragment() {
    private var _binding: FragmentBreweryDetailsBinding? = null
    private val binding: FragmentBreweryDetailsBinding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreweryDetailsBinding.inflate(
            inflater, container, false
        )

        viewSetup(arguments)
        return binding.root
    }

    private fun viewSetup(item: Bundle?) {
        item?.let {
            binding.tvName.text = item.getString("name")
            binding.tvBreweryType.text = item.getString("type")
            binding.tvStreet.text = item.getString("street")
            binding.tvCity.text = item.getString("city")
            binding.tvState.text = item.getString("state")
            binding.tvCountry.text = item.getString("country")
            binding.tvPostcode.text = item.getString("postCode")


        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}