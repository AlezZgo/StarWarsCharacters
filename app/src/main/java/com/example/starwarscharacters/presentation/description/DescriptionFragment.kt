package com.example.starwarscharacters.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.starwarscharacters.R
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding
import com.example.starwarscharacters.presentation.favourites.FavouritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DescriptionFragment : Fragment() {

    private val viewModel: DescriptionViewModel by lazy {
        ViewModelProvider(this)[DescriptionViewModel::class.java]
    }

    private var _binding: FragmentDescriptionBinding? = null
    private val binding: FragmentDescriptionBinding
        get() = _binding ?: throw RuntimeException("FragmentDescription = null")

    val args by navArgs<DescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        with(binding){
            with(args.character){
                tvName.text = name
                tvGender.text = getString(R.string.character_gender_template,gender)
                tvMass.text = getString(R.string.character_mass_template,mass)
                tvHeight.text = getString(R.string.character_height_template,height)
                tvHomeWorld.text = getString(R.string.character_homeworld_template,homeWorld)
                tvFilms.text = getString(R.string.character_films_template,films)
                tgbIsFavourite.isChecked = isFavourite
                tgbIsFavourite.setOnClickListener {
                    CoroutineScope(Dispatchers.IO+ Job()).launch {
                        val oldObj = viewModel.getCharacterUseCase(name)
                        val newObj = oldObj.copy(isFavourite = !oldObj.isFavourite)
                        viewModel.insertCharacterUseCase(newObj)
                        tgbIsFavourite.isChecked = newObj.isFavourite
                    }
                }
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance() = DescriptionFragment()
    }
}