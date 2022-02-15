package com.example.starwarscharacters.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscharacters.databinding.ItemCharacterBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        character: CharacterInfo,
        onCharacterClickListener: CharactersAdapter.OnCharacterClickListener?,
        onIsFavouriteClickListener: CharactersAdapter.OnIsFavouriteClickListener?,
    ) = with(binding) {

        tvItemName.text = character.name
        tgbFav.isChecked = character.isFavourite

        tgbFav.setOnClickListener {
            onIsFavouriteClickListener?.onIsFavouriteClick(character)
        }

        root.setOnClickListener {
            onCharacterClickListener?.onCharacterClick(character)
        }

    }
}