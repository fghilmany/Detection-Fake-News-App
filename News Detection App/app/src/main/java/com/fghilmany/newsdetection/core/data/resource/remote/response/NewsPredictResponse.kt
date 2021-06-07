package com.fghilmany.newsdetection.core.data.resource.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NewsPredictResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("value")
	val value: Value? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

@Parcelize
data class Value(

	@field:SerializedName("predict_value")
	val predictValue: List<List<Double>>,

	@field:SerializedName("label_result")
	val labelResult: String? = null
): Parcelable
