package ru.bmstu.iu7.simplemusic.avatarsservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Error(
        @JsonProperty(value = "message")
        val message: String
)
