package com.example.gamesproj.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesproj.R
import com.example.gamesproj.model.dataClasses.GameDetails

class GameAdapter  : PagingDataAdapter<GameDetails, GameAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.twx.text =
//            "${getItem(position)?.firstName} ${getItem(position)?.name}"
//        holder.itemView.textViewEmail.text = getItem(position)?.description

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
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