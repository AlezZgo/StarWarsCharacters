package com.example.starwarscharacters.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.starwarscharacters.databinding.ItemCharacterBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharactersAdapter(
    private val context: Context
) : ListAdapter<CharacterInfo, CharacterViewHolder>(CharacterInfoDiffCallback) {

    var onCharacterClickListener: OnCharacterClickListener? = null

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
        with(holder.binding) {
            with(character) {
                tvItemName.text = name

                root.setOnClickListener {
                    onCharacterClickListener?.onCharacterClick(this)
                }
            }
        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(characterInfo: CharacterInfo)
    }
}