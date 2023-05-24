package com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model

data class ResultM(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)