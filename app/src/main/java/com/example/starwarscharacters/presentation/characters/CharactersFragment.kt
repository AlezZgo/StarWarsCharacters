package com.example.starwarscharacters.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.databinding.FragmentCharactersBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.BaseFragment
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        setFilter()

    }

    private fun setAdapter(){
        val adapter = CharactersAdapter(requireContext())

        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener {
            override fun onCharacterClick(character: CharacterInfo) {
                launchDescriptionFragment(character)
            }
        }
        adapter.onIsFavouriteClickListener = object : CharactersAdapter.OnIsFavouriteClickListener {
            override fun onIsFavouriteClick(character: CharacterInfo) {
                CoroutineScope(Dispatchers.IO + Job()).launch {
                    viewModel.insertCharacterUseCase(character.copy(isFavourite = !character.isFavourite))
                }
            }
        }

        binding.rvCharacters.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setFilter(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.setFilter(it) }
                return false
            }


            override fun onQueryTextChange(query: String?): Boolean {
                query?.let { viewModel.setFilter(it) }
                return false
            }
        })
    }



    private fun launchDescriptionFragment(character: CharacterInfo) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToDescriptionFragment(character)
        )
    }

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)
}