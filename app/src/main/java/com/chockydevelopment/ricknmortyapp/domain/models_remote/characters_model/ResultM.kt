package com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model

data class ResultM(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationM: LocationM,
    val name: String,
    val originM: OriginM,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)