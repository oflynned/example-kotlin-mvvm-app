package com.syzible.search.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syzible.search.data.SearchResult
import com.syzible.search.domain.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {

    private val _searchResults: MutableLiveData<List<SearchResult>> = MutableLiveData()
    val searchResults: LiveData<List<SearchResult>> get() = _searchResults

    suspend fun search(term: String) {
        val results = getSearchResultsUseCase.execute(term)

        _searchResults.postValue(results)
    }
}
