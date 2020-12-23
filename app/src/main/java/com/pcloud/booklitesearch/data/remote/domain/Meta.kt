package com.pcloud.booklitesearch.data.remote.domain

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total_count") val totalCount:Int,
    @SerializedName("pageable_count") val pageableCount:Int,
    @SerializedName("is_end") val isEnd:Boolean
)