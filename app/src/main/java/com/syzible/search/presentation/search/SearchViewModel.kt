package com.syzible.search.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.exception.ApolloException
import com.syzible.search.data.SearchResult
import com.syzible.search.domain.GetSearchResultsUseCase
import com.syzible.search.presentation.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {

    private val _searchResults: MutableLiveData<ViewState<List<SearchResult>>> = MutableLiveData()
    val searchResults: LiveData<ViewState<List<SearchResult>>> get() = _searchResults

    suspend fun search(term: String) {
        try {
            val results = getSearchResultsUseCase.execute(term)
            _searchResults.postValue(ViewState.Success(results))
        } catch (e: ApolloException) {
            _searchResults.postValue(ViewState.Error(e.message))
        }
    }
}
