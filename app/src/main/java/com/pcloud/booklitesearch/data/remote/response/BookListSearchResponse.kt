package com.pcloud.booklitesearch.data.remote.response

import com.google.gson.annotations.SerializedName
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.data.remote.domain.Meta

data class BookListSearchResponse (
    @SerializedName("meta") val meta: Meta,
    @SerializedName("documents") val documents:ArrayList<Document>
)



