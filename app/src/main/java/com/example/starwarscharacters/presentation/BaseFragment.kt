package com.example.starwarscharacters.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.starwarscharacters.databinding.FragmentDescriptionBinding
import com.example.starwarscharacters.presentation.characters.CharactersViewModel
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, V : ViewModel> : Fragment() {

    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)

    protected lateinit var viewModel: V

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val component by lazy {
        (requireActivity().application as StarWarsApp).component
    }

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}