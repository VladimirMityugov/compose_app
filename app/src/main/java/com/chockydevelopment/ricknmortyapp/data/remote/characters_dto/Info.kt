package com.chockydevelopment.ricknmortyapp.data.remote.characters_dto

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)