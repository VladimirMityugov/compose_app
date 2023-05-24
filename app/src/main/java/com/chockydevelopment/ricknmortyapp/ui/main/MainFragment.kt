package com.chockydevelopment.ricknmortyapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.chockydevelopment.ricknmortyapp.R
import com.chockydevelopment.ricknmortyapp.databinding.FragmentMainBinding
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.ResultM
import com.chockydevelopment.ricknmortyapp.presentation.adapters.CharacterAdapter
import com.chockydevelopment.ricknmortyapp.presentation.view_models.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharacterAdapter { character -> onCharacterClick(character) }
        val progressBar = binding.progressBar
        val retryButton = binding.retryButton
        val emptyList = binding.emptyList
        val showLocations = binding.showLocationsButton
        val characterRecyclerView = binding.characterRecyclerView
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        characterRecyclerView.layoutManager = layoutManager
        characterRecyclerView.adapter = adapter

        retryButton.setOnClickListener {
            adapter.retry()
        }

        showLocations.setOnClickListener {
            onShowLocationsClick()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllCharacters(null).collectLatest {
                    adapter.submitData(it)
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect { loadState ->
                    val isListEmpty =
                        loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
//                     show empty list
                    emptyList.isVisible = isListEmpty
                    // Only show the list if refresh succeeds.
                    characterRecyclerView.isVisible = !isListEmpty

                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    retryButton.isVisible = loadState.source.refresh is LoadState.Error

                    val errorState = loadState.source.append as? LoadState.Error
                        ?: loadState.source.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        Toast.makeText(
                            requireContext(),
                            "Could not fetch the result",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun onShowLocationsClick() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack("Locations Fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentManager.beginTransaction()
            .replace(R.id.container, LocationsFragment.newInstance(), "Locations Fragment")
            .addToBackStack("Locations Fragment")
            .commit()
    }

    private fun onCharacterClick(character: ResultM) {
        viewModel.setCharacterId(character.id)
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack("Locations Fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentManager.beginTransaction()
            .replace(R.id.container, CharacterFragment.newInstance(), "Character Fragment")
            .addToBackStack("Character Fragment")
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}