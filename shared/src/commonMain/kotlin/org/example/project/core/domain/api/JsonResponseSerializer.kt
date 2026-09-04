package org.example.project.core.domain.api

interface JsonResponseSerializer <T: Any> {

    fun serialize(rawJson: String): T
}