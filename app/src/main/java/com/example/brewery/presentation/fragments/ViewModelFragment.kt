package com.example.brewery.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.brewery.presentation.BreweryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment : Fragment() {
    protected val viewModel: BreweryViewModel by activityViewModels()

}