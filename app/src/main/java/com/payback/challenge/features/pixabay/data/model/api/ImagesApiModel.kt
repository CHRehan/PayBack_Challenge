package com.payback.challenge.features.pixabay.data.model.api

import com.google.gson.annotations.SerializedName

data class ImagesApiModel(
    @SerializedName("hits")
    var images: List<ImageDetailsApiModel>?
)
