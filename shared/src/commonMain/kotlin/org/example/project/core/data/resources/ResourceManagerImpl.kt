package org.example.project.core.data.resources

import coffee.shared.generated.resources.Res

class ResourceManagerImpl : ResourceManager {
    override suspend fun getFileResource(path: String): String {
        return Res.readBytes(path).decodeToString()
    }
}