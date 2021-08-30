package com.syzible.search.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchResults: MutableLiveData<List<SearchResult>> = MutableLiveData()
    val searchResults: LiveData<List<SearchResult>> get() = _searchResults

    suspend fun search(term: String) {
        val response = repository.getResults(term)
        val results: List<SearchResult> =
            response?.edges?.mapNotNull { edge ->
                val node = edge!!.node!!
                return@mapNotNull SearchResult(
                    node.name,
                    node.abv,
                    node.category,
                    node.styles as ArrayList<String>
                )
            } ?: emptyList()

        _searchResults.postValue(results)
    }
}
