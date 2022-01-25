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
import com.example.starwarscharacters.databinding.FragmentCharactersBinding
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

        for (i in 0..20) {
            val list = listOf<CharacterInfoDbModel>(
                CharacterInfoDbModel
                    (
                    "$i",
                    "2",
                    "2",
                    "2",
                    "2",
                    "film1, film2"
                )
            )
            val characters = AppDatabase.getInstance(requireActivity().application).characterDao()
            CoroutineScope(Dispatchers.IO + Job()).launch {
                characters.insert(list)
            }
        }
        binding.root.setOnClickListener {
            val characters = charactersViewModel.characterList.value
            Toast.makeText(requireActivity().applicationContext,
                characters.toString(),
                Toast.LENGTH_LONG).show()
        }

    }
}