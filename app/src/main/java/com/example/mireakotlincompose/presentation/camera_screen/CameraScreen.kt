package com.example.mireakotlincompose.presentation.camera_screen

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mireakotlincompose.presentation.navigation.Screen
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(navController: NavHostController, viewModel: SharedViewModel = viewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var previewView by remember { mutableStateOf<PreviewView?>(null) }

    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (permissionState.status != PermissionStatus.Granted) {
        SideEffect { permissionState.launchPermissionRequest() }
    } else {
        Column {
            AndroidView(
                factory = {
                    PreviewView(context).also { preview ->
                        previewView = preview
                    }
                },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    val file = File(context.getExternalFilesDir(null), "${System.currentTimeMillis()}.jpg")
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
                    imageCapture?.takePicture(
                        outputOptions,
                        ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                val savedUri = Uri.fromFile(file)
                                viewModel.savePhoto(context, savedUri)
                                navController.navigate(Screen.ListScreen.route)
                            }

                            override fun onError(exception: ImageCaptureException) {
                                Log.e("CameraScreen", "Error saving photo: ${exception.message}", exception)
                            }
                        })
                }
            ) {
                Text("Сделать фото")
            }
        }

        LaunchedEffect(Unit) {
            val cameraProvider = ProcessCameraProvider.getInstance(context).get()

            val preview = Preview.Builder().build()
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )

                preview.setSurfaceProvider(previewView?.surfaceProvider)
            } catch (e: Exception) {
                Log.e("CameraScreen", "Camera binding failed", e)
            }
        }
    }
}