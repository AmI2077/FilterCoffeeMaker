package org.example.project.core.domain.impl

import androidx.compose.runtime.Composable

@Composable
actual fun rememberImagePicker(onResult: (ByteArray?) -> Unit): ImagePicker {
    // TODO "сделать реализацию для ios"
}

actual class ImagePicker {
    actual fun launchGallery() {
    }
}