package org.example.project.core.domain.impl

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class ImagePicker(
    private val context: Context,
    private val launchPicker: () -> Unit,
) {
    actual fun launchGallery() {
        launchPicker()
    }
}

@Composable
actual fun rememberImagePicker(onResult: (ByteArray?) -> Unit): ImagePicker {
    val context = LocalContext.current

    val picker = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    onResult(inputStream.readBytes())
                }
            } catch (_: Exception) {
                onResult(null)
            }
        } else {
            onResult(null)
        }
    }
    return remember(context, picker) {
        ImagePicker(context) {
            picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}