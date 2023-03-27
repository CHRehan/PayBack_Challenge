package com.payback.challenge.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.features.pixabay.ui.pixabaydetail.PixabayDetailScreen
import com.payback.challenge.features.pixabay.ui.pixabaylist.PixabayListScreen
import com.payback.challenge.navigation.Screens.Companion.IMAGE_ID

@ExperimentalComposeUiApi
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ImageList.route) {
        // e.g will add auth routes here if when we will extend project
        composable(Screens.ImageList.route) {
            PixabayListScreen(navController)
        }
        composable(
            Screens.ImageDetail.route
        ) {
            val imageDetail =
                navController.previousBackStackEntry?.savedStateHandle?.get<ImageDetailsUiModel>(
                    IMAGE_ID
                )
            imageDetail?.let {
                PixabayDetailScreen(it, navController)
            }
        }
    }
}
