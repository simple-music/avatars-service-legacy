package ru.bmstu.iu7.simplemusic.avatarsservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.bmstu.iu7.simplemusic.avatarsservice.exception.NotFoundException
import java.io.File

@Service
class AvatarServiceImpl(
        @Value("\${uploads.directory}")
        private val rootPath: String) : AvatarService {
    private val uploadsDir: File = File(rootPath)

    init {
        assert(this.uploadsDir.exists() || this.uploadsDir.mkdirs())
    }

    override fun addAvatar(user: String, data: ByteArray) {
        this.fileForUser(user).writeBytes(data)
    }

    override fun getAvatar(user: String): ByteArray {
        val file = this.fileForUser(user)
        return if (file.exists()) {
            file.readBytes()
        } else {
            throw NotFoundException("avatar not found")
        }
    }

    override fun deleteAvatar(user: String) {
        val file = this.fileForUser(user)
        if (file.exists()) {
            file.delete()
        } else {
            throw NotFoundException("avatar not found")
        }
    }

    private fun fileForUser(user: String): File {
        return File(this.uploadsDir, "$user.jpeg")
    }
}
