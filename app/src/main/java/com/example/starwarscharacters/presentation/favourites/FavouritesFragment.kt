package com.example.starwarscharacters.presentation.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding
import com.example.starwarscharacters.databinding.FragmentFavouritesBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.BaseFragment
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import com.example.starwarscharacters.presentation.characters.CharactersFragmentDirections
import com.example.starwarscharacters.presentation.characters.CharactersViewModel
import com.example.starwarscharacters.presentation.description.DescriptionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {

    private val viewModel: FavouritesViewModel by lazy {
        ViewModelProvider(this)[FavouritesViewModel::class.java]
    }

    override fun initBinding(inflater: LayoutInflater,container: ViewGroup?,
    ): FragmentFavouritesBinding = FragmentFavouritesBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()

    }
    private fun setUpAdapter() {
        val adapter = CharactersAdapter(requireContext())

        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener {
            override fun onCharacterClick(character: CharacterInfo) {
                launchDescriptionFragment(character)
            }
        }
        adapter.onIsFavouriteClickListener = object : CharactersAdapter.OnIsFavouriteClickListener{
            override fun onIsFavouriteClick(character: CharacterInfo) {
                CoroutineScope(Dispatchers.IO + Job() ).launch{
                    viewModel.insertCharacterUseCase(character.copy(isFavourite = !character.isFavourite))
                }
            }
        }

        binding.rvFavourites.adapter = adapter

        viewModel.favouriteCharacterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    fun launchDescriptionFragment(character: CharacterInfo){
        findNavController().navigate(
            FavouritesFragmentDirections.actionNavigationFavouritesToDescriptionFragment(character)
        )
    }


}