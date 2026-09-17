package org.example.project.core.domain.api.json

interface JsonResponseSerializer <T: Any> {

    fun deserialize(rawJson: String): T
}