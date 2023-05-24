package com.chockydevelopment.ricknmortyapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chockydevelopment.ricknmortyapp.R
import com.chockydevelopment.ricknmortyapp.databinding.CharacterItemBinding


import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.ResultM

class CharacterAdapter(
    private val onCharacterClick: (ResultM) -> Unit
) : PagingDataAdapter<ResultM, CharacterViewHolder>(CharacterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context)),
            onCharacterClick = onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

}

class CharacterViewHolder(private val binding: CharacterItemBinding,
val onCharacterClick: (ResultM) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ResultM) {
        with(binding) {
            Glide
                .with(characterImage.context)
                .load(item.image)
                .into(characterImage)

            characterName.text = item.name

            when(item.status){
                ALIVE -> characterStatusSign.setBackgroundResource(R.drawable.alive_status)
                DEAD -> characterStatusSign.setBackgroundResource(R.drawable.dead_status)
                UNKNOWN -> characterStatusSign.setBackgroundResource(R.drawable.unknown_status)
            }

            characterStatus.text = buildString {
                append(item.status.replaceFirstChar { it.uppercase() })
                append(" - ")
                append(item.species)
            }
            characterLocation.text = item.locationM.name

            root.setOnClickListener {
                onCharacterClick(item)
            }
        }
    }



    companion object {
        private const val ALIVE = "Alive"
        private const val DEAD = "Dead"
        private const val UNKNOWN = "unknown"
    }
}

class CharacterDiffUtil : DiffUtil.ItemCallback<ResultM>() {

    override fun areItemsTheSame(oldItem: ResultM, newItem: ResultM): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResultM, newItem: ResultM): Boolean {
        return oldItem.id == newItem.id
    }

}
