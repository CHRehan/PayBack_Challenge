package com.payback.challenge.features.pixabay.presentation

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.payback.challenge.R
import com.payback.challenge.features.common.Resource.Error
import com.payback.challenge.features.common.Resource.Success
import com.payback.challenge.features.pixabay.domain.usecase.ImagesUseCase
import com.payback.challenge.features.pixabay.presentation.mapper.ImageDetailsDomainToPresentationMapper
import com.payback.challenge.features.pixabay.presentation.model.ImagesViewState
import com.payback.challenge.features.pixabay.presentation.model.emptyViewState
import com.payback.challenge.features.pixabay.ui.mapper.ImageDetailsPresentationToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_SEARCH_QUERY = "fruits"

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
    private val imageDetailsDomainToPresentation: ImageDetailsDomainToPresentationMapper,
    private val resources: Resources,
    val imageDetailsToUiMapper: ImageDetailsPresentationToUiMapper
) : BaseViewModel<ImagesViewState>() {

    override fun initialState() = emptyViewState()

    private val _searchText = MutableStateFlow(INITIAL_SEARCH_QUERY)
    val searchText = _searchText.asStateFlow()

    init {
        setDataLoading()
    }

    fun getImages(query: String) {
        setDataLoading()
        viewModelScope.launch {
            imagesUseCase(query).collect { imagesResource ->
                when (imagesResource) {
                    is Success -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                images = imagesResource.data?.map { imageDetailsDomain ->
                                    imageDetailsDomainToPresentation.toPresentation(
                                        imageDetailsDomain
                                    )
                                } ?: emptyList()
                            )
                        }
                    }
                    is Error -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                errorMessage = imagesResource.message
                                    ?: resources.getString(R.string.something_went_wrong)
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun setDataLoading() {
        updateState { currentViewState -> currentViewState.copy(isDataLoading = true) }
    }
}
