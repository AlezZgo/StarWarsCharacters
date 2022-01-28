package com.example.starwarscharacters.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.starwarscharacters.R
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var viewModel: DescriptionViewModel

    private var _binding: FragmentDescriptionBinding? = null
    private val binding: FragmentDescriptionBinding
        get() = _binding ?: throw RuntimeException("FragmentDescription = null")

    val args by navArgs<DescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DescriptionViewModel::class.java]
        with(binding){
            with(args.character){
                tvName.text = name
                tvGender.text = gender
                tvMass.text = mass
                tvHeight.text = height
                tvHomeWorld.text = homeWorld
                tvFilms.text = films
                tgbIsFavourite.isChecked = isFavourite
            }
        }
    }


    companion object {
        fun newInstance() = DescriptionFragment()
    }
}