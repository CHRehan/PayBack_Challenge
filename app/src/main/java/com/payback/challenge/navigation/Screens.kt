package com.payback.challenge.navigation

sealed class Screens(val route: String) {
    // Argument Keys
    companion object {
        const val IMAGE_ID = "image_id"
    }

    object ImageList : Screens("image_list")
    object ImageDetail : Screens("image_detail")
}
