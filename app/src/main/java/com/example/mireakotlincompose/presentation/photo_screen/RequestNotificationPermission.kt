package com.example.mireakotlincompose.presentation.photo_screen

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermission(onPermissionGranted: () -> Unit) {
    val permissionState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            return
        }

    LaunchedEffect(permissionState) {
        if (permissionState.status.isGranted) {
            onPermissionGranted()
        }
    }

    if (permissionState.status.isGranted) {
        onPermissionGranted()
    } else {
        SideEffect {
            permissionState.launchPermissionRequest()
        }
    }
}