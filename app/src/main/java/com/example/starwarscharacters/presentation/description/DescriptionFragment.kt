package com.example.starwarscharacters.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscharacters.R

class DescriptionFragment : Fragment() {

    companion object {
        fun newInstance() = DescriptionFragment()
    }

    private lateinit var viewModel: DescriptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DescriptionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}