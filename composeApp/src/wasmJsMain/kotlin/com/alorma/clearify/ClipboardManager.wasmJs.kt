package com.alorma.clearify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.await
import kotlin.js.Promise

class WasmJsClipboardManager : ClipboardManager {
    override suspend fun copyToClipboard(text: String) {
        copyToClipboardExternal(text)
    }
}

@Composable
actual fun rememberClipboardManager(): ClipboardManager {
    return remember { WasmJsClipboardManager() }
}

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("(text) => navigator.clipboard.writeText(text)")
private external fun jsWriteText(text: String): Promise<JsAny?>

@OptIn(ExperimentalWasmJsInterop::class)
private suspend fun copyToClipboardExternal(text: String) {
    jsWriteText(text).await<JsAny?>()
}
