package com.chockydevelopment.ricknmortyapp.data.remote.locations_dto

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)