package com.alorma.clearify

interface ClipboardManager {
    suspend fun copyToClipboard(text: String)
}
