package com.syzible.search.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syzible.search.data.SearchResult
import com.syzible.search.databinding.SearchResultBinding

class SearchAdapter(
    private val callback: SearchResultCallback
) : RecyclerView.Adapter<SearchAdapter.SearchResultViewHolder>() {

    private var results: List<SearchResult> = emptyList()

    class SearchResultViewHolder(val binding: SearchResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = SearchResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val result: SearchResult = results[position]
        val abv = "${result.abv}%"

        holder.binding.searchResultName.text = result.name
        holder.binding.searchResultAbv.text = abv

        holder.itemView.setOnClickListener {
            callback.onClick(result)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setList(searchResults: List<SearchResult>) {
        this.results = searchResults
    }
}
