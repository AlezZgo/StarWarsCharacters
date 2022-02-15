package com.example.starwarscharacters.presentation.description

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.starwarscharacters.R
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding
import com.example.starwarscharacters.presentation.BaseFragment

class DescriptionFragment : BaseFragment<FragmentDescriptionBinding, DescriptionViewModel>(
    FragmentDescriptionBinding::inflate) {

    private val args by navArgs<DescriptionFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DescriptionViewModel::class.java]
        viewModel.initCharacter(args.character.name)
        initViewsContent()
        binding.tgbIsFavourite.setOnClickListener {
            viewModel.changeIsFavouriteStatus()
        }
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private fun initViewsContent() {
        with(binding) {
            with(args.character) {
                tvName.text = name
                tvGender.text = getString(R.string.character_gender_template, gender)
                tvMass.text = getString(R.string.character_mass_template, mass)
                tvHeight.text = getString(R.string.character_height_template, height)
                tvHomeWorld.text = getString(R.string.character_homeworld_template, homeWorld)
                tvFilms.text = getString(R.string.character_films_template, films)
                tgbIsFavourite.isChecked = isFavourite
            }
        }
    }

}