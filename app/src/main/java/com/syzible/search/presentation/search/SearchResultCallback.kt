package com.syzible.search.presentation.search

import com.syzible.search.data.SearchResult

interface SearchResultCallback {
    fun onClick(result: SearchResult)
}
