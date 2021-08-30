package com.syzible.search.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.syzible.search.databinding.FragmentSearchResultsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), TextWatcher, SearchResultCallback {

    companion object Constants {
        const val defaultSearchTerm = "Hoegaarden"
    }

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

        binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchResultRecyclerView.adapter = adapter
        binding.searchBoxEditText.addTextChangedListener(this)

        viewModel.searchResults.observe(viewLifecycleOwner, { results ->
            adapter.setList(results)
            adapter.notifyDataSetChanged()
        })

        lifecycleScope.launchWhenCreated {
            binding.searchBoxEditText.setText(defaultSearchTerm)
            viewModel.search(defaultSearchTerm)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if (!s.isNullOrEmpty()) {
            lifecycleScope.launchWhenCreated {
                viewModel.search(s.toString())
            }
        }
    }

    override fun onClick(result: SearchResult) {
        Toast.makeText(context, result.name, Toast.LENGTH_SHORT).show()
    }
}
