package com.example.starwarscharacters.presentation.characters

import android.widget.SearchView

class QueryTextChangeListener(private val block: (String) -> Unit) :
    SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { block.invoke(query) }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let { block.invoke(query) }
        return false
    }
}