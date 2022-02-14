package com.example.starwarscharacters.presentation.description

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.starwarscharacters.R
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding
import com.example.starwarscharacters.presentation.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DescriptionFragment : BaseFragment<FragmentDescriptionBinding, DescriptionViewModel>() {

    private val args by navArgs<DescriptionFragmentArgs>()

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentDescriptionBinding = FragmentDescriptionBinding.inflate(
        inflater,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DescriptionViewModel::class.java]
        initViewsContent()
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

                tgbIsFavourite.setOnClickListener {
                    CoroutineScope(Dispatchers.IO + Job()).launch {
                        val oldObj = viewModel.getCharacterUseCase(name)
                        val newObj = oldObj.copy(isFavourite = !oldObj.isFavourite)
                        viewModel.insertCharacterUseCase(newObj)
                        tgbIsFavourite.isChecked = newObj.isFavourite
                    }
                }
            }
        }
    }

}