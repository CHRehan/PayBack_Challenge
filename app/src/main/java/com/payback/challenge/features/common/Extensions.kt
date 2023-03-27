package com.payback.challenge.features.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Response
import java.text.DecimalFormat

internal fun Response<*>.getException(): Exception {
    return Exception(message())
}

internal fun <DataModel, T : DataModel> Response<T>.extractResult(): Resource<DataModel> {
    val successfulResponse = body()

    if (isSuccessful && successfulResponse != null) {
        return Resource.Success(successfulResponse)
    }

    return Resource.Error(getException().localizedMessage ?: "Something went wrong")
}

fun Long?.orZero() = this ?: 0

fun Long.format(): String = DecimalFormat("#,###.##").format(this)

fun Context.openBrowser(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
