package com.syzible.search.domain

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.syzible.search.data.SearchResult
import com.syzible.search.GetSearchResultsQuery
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(
    private val client: ApolloClient
) {
    suspend fun execute(query: String): List<SearchResult> {
        val response = client.query(GetSearchResultsQuery(query)).await()
        val results =
            response.data?.getSearchResults?.edges?.map(
                fun(edge: GetSearchResultsQuery.Edge?): SearchResult {
                    val node = edge?.node!!
                    return SearchResult(node.name, node.abv, node.category, node.styles as ArrayList<String>)
                }
            )

        return results ?: emptyList()
    }
}
