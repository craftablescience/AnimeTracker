package com.craftablescience.animetracker.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import co.anilist.graphql.ViewerQuery
import com.apollographql.apollo.coroutines.toDeferred
import com.craftablescience.animetracker.R
import com.craftablescience.animetracker.fragments.ListFragment
import com.craftablescience.animetracker.util.apolloClient

class CurrentlyWatchingFragment : ListFragment() {
    private lateinit var root : View
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.currentlywatching_fragment, container, false)

        recyclerView = root.findViewById(R.id.currentlywatching_recycler)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            val response = apolloClient.query(ViewerQuery()).toDeferred().await()
            //response.data.toString()
        }
    }
}