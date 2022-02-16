package com.example.starwarscharacters.presentation.favourites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.databinding.FragmentFavouritesBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.BaseFragment
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, FavouritesViewModel>(
    FragmentFavouritesBinding::inflate) {

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
        val adapter = CharactersAdapter(
            object : CharactersAdapter.OnCharacterClickListener {
                override fun onCharacterClick(character: CharacterInfo) {
                    findNavController().navigate(
                        FavouritesFragmentDirections.actionNavigationFavouritesToDescriptionFragment(
                            character)
                    )
                }
            },
            object : CharactersAdapter.OnIsFavouriteClickListener {
                override fun onIsFavouriteClick(character: CharacterInfo) {
                    viewModel.changeIsFavouriteStatus(character)
                }
            })

        binding.favouritesRecyclerview.adapter = adapter

        viewModel.favouriteCharacterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}