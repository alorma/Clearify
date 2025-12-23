package com.alorma.clearify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.window
import kotlin.js.Promise

class JsClipboardManager : ClipboardManager {
    override suspend fun copyToClipboard(text: String) {
        window.navigator.clipboard.writeText(text).await()
    }
}

suspend fun <T> Promise<T>.await(): T = kotlin.coroutines.suspendCoroutine { cont ->
    then(
        onFulfilled = { cont.resumeWith(Result.success(it)) },
        onRejected = { cont.resumeWith(Result.failure(Exception(it.toString()))) }
    )
}

@Composable
actual fun rememberClipboardManager(): ClipboardManager {
    return remember { JsClipboardManager() }
}
