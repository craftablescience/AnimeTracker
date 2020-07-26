package com.craftablescience.animetracker.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.craftablescience.animetracker.R
import com.craftablescience.animetracker.fragments.ListFragment

class DroppedFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dropped_fragment, container, false)
    }
}