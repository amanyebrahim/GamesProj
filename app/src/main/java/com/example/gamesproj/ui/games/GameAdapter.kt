package com.example.gamesproj.ui.games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesproj.R
import com.example.gamesproj.databinding.GameItemBinding
import com.example.gamesproj.model.dataClasses.GameDetails
import com.example.gamesproj.utils.NAV_KEY_GAME_ID
import com.example.gamesproj.utils.navOptions

class GameAdapter  : PagingDataAdapter<GameDetails, GameAdapter.ViewHolder>(DataDifferntiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= getItem(position)
        item?.let { holder.bind(createOnClickListener(it),it) }
    }

    class ViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener,item: GameDetails) {
            binding.apply {
               gameItem=item
                clickListener = listener
                executePendingBindings()
            }
        }
    }

    private fun createOnClickListener(gameItem: GameDetails?): View.OnClickListener {
        return View.OnClickListener {
            val bundle = bundleOf(
                NAV_KEY_GAME_ID to gameItem?.id,
            )
            it.findNavController().navigate(R.id.gameDetailsFragment, bundle, navOptions)
        }
    }
    object DataDifferntiator : DiffUtil.ItemCallback<GameDetails>() {
        override fun areItemsTheSame(oldItem: GameDetails, newItem: GameDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameDetails, newItem: GameDetails): Boolean {
            return oldItem == newItem
        }
    }
}