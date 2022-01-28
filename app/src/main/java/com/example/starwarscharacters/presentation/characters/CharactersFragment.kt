package com.example.starwarscharacters.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.datasource.RemoteDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.databinding.FragmentCharactersBinding
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null ")

    private val remoteDataSourceImpl = RemoteDataSourceImpl()
    private lateinit var characterDao : CharacterDao
    private val mapper = CharacterMapper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

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

        binding.rvCharacters.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        CoroutineScope(Dispatchers.IO + Job()).launch {
            try {
                characterDao = AppDatabase.getInstance(requireContext()).characterDao()

//                val a = remoteDataSourceImpl.getAllCharacters()
//                Log.i("logi",a.toString())
//                remoteDataSourceImpl.getAllCharacters().forEach { newCharacterDto ->
//                    val oldCharacterDbModel = characterDao.getCharacter(newCharacterDto.name)
//                    characterDao.insert(mapper.mapDtoToDbModel(
//                        newCharacterDto,
//                        isFavourite = oldCharacterDbModel.value?.isFavourite ?: false
//                    ))
//                }
            } catch (e: Exception) {
            }
        }
        return binding.root
    }

    fun launchDescriptionFragment(character: CharacterInfo){
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToDescriptionFragment(character)
        )
    }
}