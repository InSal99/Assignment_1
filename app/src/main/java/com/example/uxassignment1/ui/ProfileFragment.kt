package com.example.uxassignment1.ui

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.FragmentProfileBinding
import java.io.File

class ProfileFragment : Fragment() {
    companion object{
        var EXTRA_VALUE = "extra_value"
    }


    fun handleBackPress() {
        parentFragmentManager.popBackStack()
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    /*--------------Pick Image----------------*/
    val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it) // Display image
            Log.d("ImagePicker", "Selected Image URI: $it")
        }
    }

    // Trigger the image picker
    fun pickImage() {
        pickImageLauncher.launch("image/*") // Filter for images
    }
    /*--------------Pick Image----------------*/

    /*------------Capture Photo---------------*/

//    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//        if (isGranted) {
//            captureAndSavePhoto()  // Proceed if permission is granted
//        } else {
//            Toast.makeText(requireContext(), "Camera permission required", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//
//    // Declare a constant for the request code
//    private val CAMERA_REQUEST_CODE = 100
//
//    // Declare the launcher for taking the picture
//    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
//        if (success) {
//            imageView.setImageURI(imageUri)
//            Log.d("Camera", "Image saved at: $imageUri")
//        } else {
//            Log.e("Camera", "Image capture failed")
//            Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun checkCameraPermission() {
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            captureAndSavePhoto()
//        } else {
//            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//        }
//    }
//
//    // Handle the permission request result
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == CAMERA_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, proceed with camera
//                captureAndSavePhoto()
//            } else {
//                // Permission denied, show a message to the user
//                Toast.makeText(requireContext(), "Camera permission is required to take a photo.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun captureAndSavePhoto() {
//        val photoUri = createPhotoUri()
//
//        photoUri?.let { uri ->
//            // Grant permission for the camera to write to the file
//            requireContext().grantUriPermission("com.android.camera", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            takePictureLauncher.launch(uri)
//        } ?: run {
//            Toast.makeText(requireContext(), "Failed to create photo URI.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    //     Helper function to create the Uri for the photo
//    private fun createPhotoUri(): Uri? {
//        val photoFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo_${System.currentTimeMillis()}.jpg")
//        val directory = photoFile.parentFile
//        if (!directory.exists()) {
//            directory.mkdirs()
//        }
//        return if (directory.exists()) {
//            FileProvider.getUriForFile(requireContext(), "com.example.uxassignment1.fileprovider", photoFile)
//        } else {
//            null
//        }
//    }







    // Declare a constant for the request code
    private val CAMERA_REQUEST_CODE = 100

    // Declare the ActivityResultLauncher for taking a picture
    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess: Boolean ->
        if (isSuccess) {
            Log.d("Camera", "Captured Image")
            binding.ivPhoto.setImageURI(imageUri)
        } else {
            Log.d("Camera", "Failed to capture image")
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        } else {
            // Permission granted, proceed with capturing the photo
            capturePhoto()
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with camera
                capturePhoto()
            } else {
                // Permission denied, notify the user
                Toast.makeText(requireContext(), "Camera permission is required to take a photo.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    // Trigger the camera
//    private fun capturePhoto() {
//        // Create a Uri to save the captured photo (using FileProvider)
//        val photoUri: Uri? = createPhotoUri()
//
//        // Check if the Uri is valid
//        photoUri?.let {
//            // Launch the camera using the takePictureLauncher
//            takePictureLauncher.launch(it)
//        }
//    }

    // Trigger the camera
    private fun capturePhoto() {
        // Create a Uri to save the captured photo (using FileProvider)
        imageUri = createPhotoUri()

        // Check if the Uri is valid
        imageUri?.let {
            // Launch the camera using the takePictureLauncher
            takePictureLauncher.launch(it)
        } ?: run {
            Toast.makeText(requireContext(), "Failed to create photo URI", Toast.LENGTH_SHORT).show()
        }
    }

    // Helper function to create the Uri for the photo
    private fun createPhotoUri(): Uri? {
        // Use app-specific storage directory for saving photos (scoped storage)
        val photoFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo_${System.currentTimeMillis()}.jpg")

        // Ensure the directory exists, create if it doesn't
        val directory = photoFile.parentFile
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Use FileProvider to create the Uri for the photo file
        return if (directory.exists()) {
            FileProvider.getUriForFile(requireContext(), "com.example.uxassignment1.fileprovider", photoFile)
        } else {
            null
        }
    }
    /*------------Capture Photo---------------*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!=null) {
            val label = arguments?.getString(EXTRA_VALUE)
            binding.tvTitle.text = label
        }

        binding.btnGoToHome.setOnClickListener {
            val fragmentHome = HomeFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.navigation_container, fragmentHome, HomeFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnPickImage.setOnClickListener {
            pickImage()
        }

        binding.btnCapturePhoto.setOnClickListener {
            checkCameraPermission()
            Log.d("tee", "Captured Image pass")
        }

        binding.btnBack.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                activity?.finish()
            }
        }

    }

}