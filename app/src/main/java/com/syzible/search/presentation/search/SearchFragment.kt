package com.syzible.search.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.syzible.search.data.SearchResult
import com.syzible.search.databinding.FragmentSearchResultsBinding
import com.syzible.search.presentation.state.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment(), SearchResultCallback {
    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val adapter = SearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBoxEditText.setOnDebounceTextWatcher(lifecycle) { input ->
            run {
                if (input.isNotEmpty()) {
                    lifecycleScope.launchWhenCreated {
                        viewModel.search(input)
                    }
                }
            }
        }

        binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchResultRecyclerView.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner, { result ->
            when (result) {
                is ViewState.Loading -> {
                }
                is ViewState.Success -> {
                    adapter.setList(result.value!!)
                    adapter.notifyDataSetChanged()
                }
                is ViewState.Error -> {
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchBoxEditText.removeOnDebounceTextWatcher()
        _binding = null
    }

    override fun onClick(result: SearchResult) {
        val action = SearchFragmentDirections.actionSearchResultsToDetailView(
            result.name,
            "${result.abv}%",
            result.styles.toTypedArray()
        )

        findNavController().navigate(action)
    }
}
