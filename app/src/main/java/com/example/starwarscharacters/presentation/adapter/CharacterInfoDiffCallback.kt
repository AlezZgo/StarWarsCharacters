package com.example.starwarscharacters.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarscharacters.domain.entities.CharacterInfo

object CharacterInfoDiffCallback : DiffUtil.ItemCallback<CharacterInfo>() {

    override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
        return oldItem == newItem
    }
}