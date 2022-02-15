package com.example.starwarscharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.starwarscharacters.databinding.ItemCharacterBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharactersAdapter(
    private val onCharacterClickListener: OnCharacterClickListener,
    private val onIsFavouriteClickListener: OnIsFavouriteClickListener,
) : ListAdapter<CharacterInfo, CharacterViewHolder>(CharacterInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character, onCharacterClickListener, onIsFavouriteClickListener)
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(character: CharacterInfo)
    }

    interface OnIsFavouriteClickListener {
        fun onIsFavouriteClick(character: CharacterInfo)
    }

}