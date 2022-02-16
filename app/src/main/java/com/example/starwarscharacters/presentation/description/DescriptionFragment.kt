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
        viewModel.character(args.character).observe(viewLifecycleOwner) { currentCharacter ->
            with(binding) {
                tvName.text = currentCharacter.name
                tvGender.text =
                    getString(R.string.character_gender_template, currentCharacter.gender)
                tvMass.text = getString(R.string.character_mass_template, currentCharacter.mass)
                tvHeight.text =
                    getString(R.string.character_height_template, currentCharacter.height)
                tvHomeWorld.text =
                    getString(R.string.character_homeworld_template, currentCharacter.homeWorld)
                tvFilms.text = getString(R.string.character_films_template, currentCharacter.films)
                tgbIsFavourite.isChecked = currentCharacter.isFavourite

                tgbIsFavourite.setOnClickListener {
                    viewModel.changeIsFavouriteStatus(currentCharacter)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

}