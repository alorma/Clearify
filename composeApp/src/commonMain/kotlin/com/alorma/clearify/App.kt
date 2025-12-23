package com.alorma.clearify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
  MaterialTheme {
    val clipboardManager = rememberClipboardManager()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
      snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { data ->
          Snackbar(snackbarData = data)
        }
      }
    ) {
      Column(
        modifier = Modifier
          .background(MaterialTheme.colorScheme.primaryContainer)
          .safeContentPadding()
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Button(onClick = {
          coroutineScope.launch {
            clipboardManager.copyToClipboard("/clear")
            snackbarHostState.showSnackbar("Copied to clipboard")
          }
        }) {
          Text("Copy /clear")
        }
      }
    }
  }
}

@Composable
expect fun rememberClipboardManager(): ClipboardManager