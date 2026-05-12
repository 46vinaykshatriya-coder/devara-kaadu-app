package com.example.devarakaadu.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class VisionRequest(

    val model: String,

    val messages: List<VisionMessage>
)

data class VisionMessage(

    val role: String,

    val content: List<ContentPart>
)

data class ContentPart(

    val type: String,

    val text: String? = null,

    val image_url: ImageData? = null
)

data class ImageData(

    val url: String
)

data class VisionResponse(

    val choices: List<Choice>
)

data class Choice(

    val message: ResponseMessage
)

data class ResponseMessage(

    val content: String
)

interface OpenRouterApi {

    @Headers(

        "Authorization: Bearer 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",

        "HTTP-Referer: https://openrouter.ai",

        "X-Title: DevaraKaadu"
    )

    @POST("v1/chat/completions")

    suspend fun generateVisionContent(

        @Body request: VisionRequest
    ): Response<VisionResponse>
}