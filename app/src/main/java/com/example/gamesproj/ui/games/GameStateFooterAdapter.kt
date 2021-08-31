package com.example.gamesproj.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesproj.databinding.LayoutFooterItemBinding
import com.example.gamesproj.utils.extension.showIf

typealias ListFooterRetryClickListener = () -> Unit

class GameStateFooterAdapter : LoadStateAdapter<GameStateFooterAdapter.ListLoadStateViewHolder>() {

    var onRetryClickListener: ListFooterRetryClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ListLoadStateViewHolder {
        val binding = LayoutFooterItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListLoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ListLoadStateViewHolder(private val binding: LayoutFooterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                progressLoading.showIf(loadState is LoadState.Loading)
                tvError.showIf(loadState !is LoadState.Loading)
                btRetry.apply {
                    showIf(loadState !is LoadState.Loading)
                    setOnClickListener {
                        onRetryClickListener?.invoke()
                    }
                }
            }
        }
    }
}