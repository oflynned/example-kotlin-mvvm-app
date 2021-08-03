package com.syzible.search.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syzible.search.R

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var results: List<SearchResult> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.search_result_name)
        val abv: TextView = view.findViewById(R.id.search_result_abv)
        val styles: TextView = view.findViewById(R.id.search_result_styles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: SearchResult = results[position]

        holder.name.text = result.name
        holder.abv.text = "${result.abv}%"

        if (result.styles.isNullOrEmpty()) {
            holder.styles.visibility = View.GONE
        } else {
            holder.styles.text = result.styles.joinToString(", ")
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setList(searchResults: List<SearchResult>) {
        this.results = searchResults
    }
}
