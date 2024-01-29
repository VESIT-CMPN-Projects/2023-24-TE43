package com.example.empoweher.model

import kotlinx.serialization.Serializable


@Serializable
data class Quote(
    var quote:String=""
)
@Serializable
data class QuoteRepo(
    val quotes:List<Quote>
)