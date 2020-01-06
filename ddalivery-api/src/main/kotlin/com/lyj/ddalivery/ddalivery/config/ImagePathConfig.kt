package com.lyj.ddalivery.ddalivery.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ddalivery.path")
data class ImagePathConfig(
        var productImagePath : String = "a"
)