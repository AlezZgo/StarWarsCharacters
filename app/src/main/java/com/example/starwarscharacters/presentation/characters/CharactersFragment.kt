package com.example.starwarscharacters.presentation.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.databinding.FragmentCharactersBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.BaseFragment
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    FragmentCharactersBinding::inflate) {

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

        val adapter = CharactersAdapter(
            object : CharactersAdapter.OnCharacterClickListener {
                override fun onCharacterClick(character: CharacterInfo) {
                    findNavController().navigate(
                        CharactersFragmentDirections.actionNavigationCharactersToDescriptionFragment(
                            character)
                    )
                }
            },
            object : CharactersAdapter.OnIsFavouriteClickListener {
                override fun onIsFavouriteClick(character: CharacterInfo) {
                    viewModel.changeIsFavouriteStatus(character)
                }
            })

        binding.rvCharacters.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setFilter() {
        binding.searchView.setOnQueryTextListener(
            QueryTextChangeListener {
                viewModel.setFilter(it)
            })
    }

}