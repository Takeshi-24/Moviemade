package org.michaelbel.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "keywords")
data class KeywordLocal(
        @ColumnInfo(name = "keyword_id") @PrimaryKey val id: Long = 0L,
        @ColumnInfo(name = "keyword_name") val name: String? = null,
        @ColumnInfo(name = "movie_id") val movieId: Long = 0L
): Serializable