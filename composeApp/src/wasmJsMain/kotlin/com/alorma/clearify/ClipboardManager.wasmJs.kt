package com.alorma.clearify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class WasmJsClipboardManager : ClipboardManager {
    override fun copyToClipboard(text: String) {
        copyToClipboardExternal(text)
    }
}

@Composable
actual fun rememberClipboardManager(): ClipboardManager {
    return remember { WasmJsClipboardManager() }
}

private fun copyToClipboardExternal(text: String) {
    js("navigator.clipboard.writeText(text)")
}
