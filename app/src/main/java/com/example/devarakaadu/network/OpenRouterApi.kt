package com.example.devarakaadu.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class VisionRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: List<ContentPart>
)

data class ContentPart(
    val type: String,
    val text: String? = null,
    val image_url: ImageUrl? = null
)

data class ImageUrl(
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
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )

    @POST("api/v1/chat/completions")

    suspend fun generateVisionContent(

        @Body
        request: VisionRequest

    ): Response<VisionResponse>
}