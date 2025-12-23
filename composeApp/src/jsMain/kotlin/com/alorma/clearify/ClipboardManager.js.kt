package com.alorma.clearify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.window

class JsClipboardManager : ClipboardManager {
    override fun copyToClipboard(text: String) {
        window.navigator.clipboard.writeText(text)
    }
}

@Composable
actual fun rememberClipboardManager(): ClipboardManager {
    return remember { JsClipboardManager() }
}
