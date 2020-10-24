/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.mvi.utility.extension

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

const val REQUEST_CODE_PERMISSION: Int = 0x0001
val requiredPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

fun Context.isPermissionGranted(): Boolean = requiredPermissions.all { isPermissionGranted(it) }
fun Context.isPermissionGranted(permission: String): Boolean = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.isPermissionGranted(): Boolean = requiredPermissions.all { isPermissionGranted(it) }
fun Fragment.isPermissionGranted(permission: String): Boolean = ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
fun Fragment.requestPermissions() = requestPermission(requiredPermissions)
fun Fragment.requestPermission(permissions: Array<String>) = requestPermissions(permissions, REQUEST_CODE_PERMISSION)

fun Activity.requestPermissions() = requestPermission(requiredPermissions)
fun Activity.requestPermission(permissions: Array<String>) = requestPermissions(permissions, REQUEST_CODE_PERMISSION)
fun Activity.isPermissionRationaleAvailable() = requiredPermissions.all { isPermissionRationaleAvailable(it) }
fun Activity.isPermissionRationaleAvailable(permission: String): Boolean =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
