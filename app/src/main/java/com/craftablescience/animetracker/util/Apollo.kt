package com.craftablescience.animetracker.util

import com.apollographql.apollo.ApolloClient

val apolloClient = ApolloClient.builder()
    .serverUrl("https://graphql.anilist.co/")
    .build()