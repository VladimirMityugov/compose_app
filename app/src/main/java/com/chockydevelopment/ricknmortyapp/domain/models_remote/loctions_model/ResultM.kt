package com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model

data class ResultM(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)