package com.example.starwarscharacters.presentation.characters

import android.content.Context
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
import com.example.starwarscharacters.presentation.StarWarsApp
import com.example.starwarscharacters.presentation.ViewModelFactory
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import javax.inject.Inject

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersViewModel::class.java]

        setUpAdapter()

        setFilter()

    }

    private fun setUpAdapter() {

        val adapter = CharactersAdapter()

        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener {
            override fun onCharacterClick(character: CharacterInfo) {
                findNavController().navigate(
                    CharactersFragmentDirections.actionNavigationCharactersToDescriptionFragment(character)
                )
            }
        }
        adapter.onIsFavouriteClickListener = object : CharactersAdapter.OnIsFavouriteClickListener {
            override fun onIsFavouriteClick(character: CharacterInfo) {
                viewModel.changeIsFavouriteStatus(character)
            }
        }

        binding.rvCharacters.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setFilter() {
        binding.searchView.setOnQueryTextListener(
            QueryTextChangeListener{
                viewModel.setFilter(it)
            })
    }

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)
}