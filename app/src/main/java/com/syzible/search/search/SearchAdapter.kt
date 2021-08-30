package com.syzible.search.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syzible.search.R

class SearchAdapter(
    private val searchResultCallback: SearchResultCallback
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var results: List<SearchResult> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.search_result_name)
        val abv: TextView = view.findViewById(R.id.search_result_abv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: SearchResult = results[position]
        val abv = "${result.abv}%"

        holder.name.text = result.name
        holder.abv.text = abv

        holder.itemView.setOnClickListener {
            searchResultCallback.onClick(result)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setList(searchResults: List<SearchResult>) {
        this.results = searchResults
    }
}
