package org.example.project.core.data

// TODO "Разгрести конфиг, понять что мне нужно и что нет"

object AiConfig {

    private const val TIMEOUT_MILLIS = 300000L

    private const val MAX_TOKENS = 1500
    private const val TEMPERATURE = 0.2
    private const val REASONING_EFFORT = "none"

    private const val YANDEX_CLOUD_API_KEY = "AQVN2fRbj27e9s7UYj2wtah5MDW-11nJ83qxndyr"
    private const val YANDEX_CLOUD_FOLDER: String = "b1gmek2o6f58ld4b8iaq"

    private const val YANDEX_CLOUD_QWEN_MODEL: String = "qwen3.6-35b-a3b/latest"
    private const val YANDEX_CLOUD_GPT_PRO_MODEL: String = "yandexgpt-5.1/latest"

    private const val YANDEX_CLOUD_BASE_URL: String = "https://ai.api.cloud.yandex.net/v1/responses"

    fun getTimeoutMillis() = TIMEOUT_MILLIS

    fun getApi(): String = YANDEX_CLOUD_API_KEY
    fun getYandexCloudFolder(): String = YANDEX_CLOUD_FOLDER
    fun getYandexCloudBaseUrl(): String = YANDEX_CLOUD_BASE_URL

    fun getQwenModelId(): String {
        return "gpt://${YANDEX_CLOUD_FOLDER}/${YANDEX_CLOUD_QWEN_MODEL}"
    }

    fun getGptProModelId(): String {
        return "gpt://${YANDEX_CLOUD_FOLDER}/${YANDEX_CLOUD_GPT_PRO_MODEL}"
    }
}