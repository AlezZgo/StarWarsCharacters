package com.example.starwarscharacters.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.databinding.FragmentCharactersBinding
import com.example.starwarscharacters.domain.CharacterInfo
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private lateinit var charactersViewModel: CharactersViewModel

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null ")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersViewModel =
            ViewModelProvider(this)[CharactersViewModel::class.java]

        setAdapter()

    }

    fun setAdapter(){
        val adapter = CharactersAdapter(requireContext())
        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener{
            override fun onCharacterClick(characterInfo: CharacterInfo) {
                Toast.makeText(requireContext(),"hello",Toast.LENGTH_LONG).show()
            }
        }
        binding.rvCharacters.adapter = adapter
        charactersViewModel.characterList.observe(requireActivity()){
            adapter.submitList(it)
        }
    }
}