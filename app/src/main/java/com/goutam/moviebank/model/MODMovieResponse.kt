package com.goutam.moviebank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MODMovieResponse(
        val page: Int = 0,
        val results: List<MovieResult>? = null,
        val status_code: Int = 0,
        val status_message: String? = null,
        val total_pages: Int = 0,
        val total_results: Int = 0
): Parcelable
