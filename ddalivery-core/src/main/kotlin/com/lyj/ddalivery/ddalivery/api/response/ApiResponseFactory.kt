package com.lyj.ddalivery.ddalivery.api.response

class ApiResponseFactory {

    companion object {
        fun <T> createOK(data: T): ApiResponse<T> {
            return ApiResponse<T>(ApiResponseCode.OK, data)
        }

        fun createException(e: ApiException): ApiResponse<ApiException> {
            return ApiResponse(e.status, e)
        }

        fun createException(code: ApiResponseCode, message: String): ApiResponse<String> {
            return ApiResponse(code, message, "")
        }

        fun <T> createException(code: ApiResponseCode, data: T): ApiResponse<T> {
            return ApiResponse<T>(code, data)
        }
    }
}