package com.alorma.clearify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WasmJsClipboardManager : ClipboardManager {
    override suspend fun copyToClipboard(text: String) {
        copyToClipboardExternal(text)
    }
}

@Composable
actual fun rememberClipboardManager(): ClipboardManager {
    return remember { WasmJsClipboardManager() }
}

private suspend fun copyToClipboardExternal(text: String) = suspendCoroutine<Unit> { cont ->
    try {
        js("navigator.clipboard.writeText(text).then(() => cont.resume(kotlin.Unit)).catch((e) => cont.resumeWithException(new Error(e)))")
    } catch (e: Throwable) {
        cont.resumeWithException(e)
    }
}
