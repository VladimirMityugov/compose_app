package com.chockydevelopment.ricknmortyapp.data.remote.episode_dto

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)