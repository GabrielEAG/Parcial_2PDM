package com.pdmcourse.spotlyfe.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
  title: String,
  canGoBack: Boolean = false,
  onBackClicked: () -> Unit = {}
) {
  TopAppBar(
    title = { Text(title) },
    navigationIcon = {
      if (canGoBack) {
        IconButton(onClick = onBackClicked) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver atrás"
          )
        }
      }
    }
  )
}