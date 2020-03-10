package com.example.chucknorrisapi.view

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisapi.R
import com.example.chucknorrisapi.model.ChuckResponseList
import com.example.chucknorrisapi.model.ItemJoke

class ChuckAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ChuckViewTypes {
        LoadingType(),
        JokeType()
    }

    var dataSet: ChuckResponseList? = null

    override fun getItemViewType(position: Int): Int {
        return if (position == 19)
            ChuckViewTypes.LoadingType.ordinal
        else
            ChuckViewTypes.JokeType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ChuckViewTypes.JokeType.ordinal -> {
                ChuckJokeViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.layout_chuck_joke_item,
                            parent,
                            false
                        )
                )
            }
            ChuckViewTypes.LoadingType.ordinal -> {
                ChuckLoadingJokesViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.layout_chuck_joke_loading,
                            parent,
                            false
                        )
                )
            }
            else ->
                throw Exception("No ViewHolder implemented...")
        }
    }

    override fun getItemCount(): Int =
        dataSet?.value?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChuckJokeViewHolder -> {
                dataSet?.value?.get(position)?.let {
                    holder.onBind(it)
                }
            }
        }
    }

    inner class ChuckJokeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvJokeItem: TextView = itemView.findViewById(R.id.tv_chuck_item_layout)

        fun onBind(itemJoke: ItemJoke) {
            tvJokeItem.text = itemJoke.joke
        }
    }

    inner class ChuckLoadingJokesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }
}