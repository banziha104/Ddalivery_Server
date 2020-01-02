package com.lyj.ddalivery.ddalivery.core

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors


interface ApiDocumentUtils {
    companion object {
        val documentRequest: OperationRequestPreprocessor?
            get() = Preprocessors.preprocessRequest(
                    Preprocessors.modifyUris()
                            .scheme("https")
                            .host("docs.api.com")
                            .removePort(),
                    Preprocessors.prettyPrint())

        val documentResponse: OperationResponsePreprocessor?
            get() {
                return Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
            }
    }
}