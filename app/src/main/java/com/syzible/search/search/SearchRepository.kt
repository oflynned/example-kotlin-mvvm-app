package com.syzible.search.search

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.syzible.search.GetSearchResultsQuery
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val client: ApolloClient
) {
    suspend fun getResults(query: String): GetSearchResultsQuery.GetSearchResults? {
        val response = client.query(GetSearchResultsQuery(query)).await()

        return response.data?.getSearchResults
    }
}
