package org.example.project.core.domain.impl

import androidx.compose.runtime.Composable

expect class ImagePicker {
    fun launchGallery()
}

@Composable
expect fun rememberImagePicker(onResult: (ByteArray?) -> Unit): ImagePicker