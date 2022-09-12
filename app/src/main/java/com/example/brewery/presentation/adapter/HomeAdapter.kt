package com.example.brewery.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brewery.databinding.BreweryListBinding
import com.example.brewery.domain.model.BreweryItem

class HomeAdapter  (
    private val breweryList: MutableList<BreweryItem> = mutableListOf(),
    private val openBreweryDetails: (BreweryItem) -> Unit
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    @SuppressLint("NotifyDataChanged")
    fun setPeopleList(newList: List<BreweryItem>, shouldUpdateList: Boolean) {
        if (shouldUpdateList) {
            breweryList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        } else {
            breweryList.clear()
            breweryList.addAll(newList)
            // works like notifySetChanged, but without the warning
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class HomeViewHolder(
        private val binding: BreweryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BreweryItem, openBreweryDetails:(BreweryItem) -> Unit) {
            binding.tvName.text = item.name
            binding.tvLocation.text = item.city + " " + item.state
            binding.btnDetails.setOnClickListener {
                openBreweryDetails(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            BreweryListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(breweryList[position], openBreweryDetails)
    }

    override fun getItemCount(): Int = breweryList.size
}