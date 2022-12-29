package com.example.secretnotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    var title: String,
    var content: String,
    var timestamp: Long,
    var imageUrl: String,
    var isEdited: Boolean,
    @PrimaryKey val id: Int? = null
) : Serializable
