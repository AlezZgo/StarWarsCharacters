package com.example.starwarscharacters.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscharacters.databinding.FragmentCharactersBinding

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
    }
}