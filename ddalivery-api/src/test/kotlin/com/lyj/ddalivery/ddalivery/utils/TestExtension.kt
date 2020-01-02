package com.lyj.ddalivery.ddalivery.utils

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType

fun FieldDescriptor.number() : FieldDescriptor{
    this.type(JsonFieldType.NUMBER)
    return this
}

fun FieldDescriptor.string() : FieldDescriptor{
    this.type(JsonFieldType.STRING)
    return this
}

fun FieldDescriptor.array() : FieldDescriptor{
    this.type(JsonFieldType.ARRAY)
    return this
}

fun FieldDescriptor.`null`() : FieldDescriptor{
    this.type(JsonFieldType.NULL)
    return this
}

fun FieldDescriptor.`object`() : FieldDescriptor{
    this.type(JsonFieldType.OBJECT)
    return this
}