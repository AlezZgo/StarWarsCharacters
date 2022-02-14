package com.example.starwarscharacters.presentation.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.databinding.FragmentFavouritesBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.BaseFragment
import com.example.starwarscharacters.presentation.StarWarsApp
import com.example.starwarscharacters.presentation.ViewModelFactory
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding,FavouritesViewModel>() {

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentFavouritesBinding = FragmentFavouritesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouritesViewModel::class.java]
        setUpAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private fun setUpAdapter() {
        val adapter = CharactersAdapter()

        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener {
            override fun onCharacterClick(character: CharacterInfo) {
                launchDescriptionFragment(character)
            }
        }
        adapter.onIsFavouriteClickListener = object : CharactersAdapter.OnIsFavouriteClickListener {
            override fun onIsFavouriteClick(character: CharacterInfo) {
                viewModel.changeIsFavouriteStatus(character)
            }
        }

        binding.rvFavourites.adapter = adapter

        viewModel.favouriteCharacterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun launchDescriptionFragment(character: CharacterInfo) {
        findNavController().navigate(
            FavouritesFragmentDirections.actionNavigationFavouritesToDescriptionFragment(character)
        )
    }


}