package com.thyagoneves

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.thyagoneves.easy_view_screenshot.R
import java.io.File
import java.io.FileOutputStream
import java.util.*

private val permissoes = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
private lateinit var bitmap: Bitmap

class Helper {
    private var pathPar: String? = null
    private var folderNamePar: String? = null
    private var viewIdPar: View? = null
    private var shareImagePar: Boolean? = null
    private var activityPar: Activity? = null

    fun takeScreenshotAndSaveImage(
        path: String?,
        folderNameH: String?,
        viewId: View,
        share: Boolean?,
        activity: Activity?
    ) {
        this.pathPar = path
        this.folderNamePar = folderNameH
        this.viewIdPar = viewId
        this.shareImagePar = share
        this.activityPar = activity

        takeScreenshotHelper()
    }

    private fun takeScreenshotHelper() {

        if (viewIdPar != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                bitmap = getBitmapFromView(viewIdPar!!)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    storeAndShareImageScopeStorage(
                        bitmap,
                        "screenshot_image_${System.currentTimeMillis()}"
                    )
                } else {
                    storeImageOldVersions(bitmap)
                }
            }, 100)
        } else {
            Toast.makeText(activityPar, activityPar?.getString(R.string.image_was_not_saved), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBitmapFromView(viewIdPar: View): Bitmap {
        bitmap = Bitmap.createBitmap(viewIdPar.width, viewIdPar.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(ContextCompat.getColor(activityPar as Activity, R.color.white))
        viewIdPar.draw(canvas)
        return bitmap

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun storeAndShareImageScopeStorage(bitmap: Bitmap?, text: String) {
        if (ContextCompat.checkSelfPermission(
                activityPar!!.applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activityPar!!.applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val contentResolver = activityPar?.contentResolver
                val contentValues = ContentValues()
                contentValues.put(
                    MediaStore.MediaColumns.DISPLAY_NAME,
                    "$pathPar.jpg"
                )
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "$folderNamePar"
                )
                val imageUri =
                    contentResolver?.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                    )
                val outPutStream =
                    contentResolver?.openOutputStream(Objects.requireNonNull(imageUri!!))
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream)
                Objects.requireNonNull(outPutStream)
                Toast.makeText(activityPar, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show()
                if (shareImagePar!!) {
                    shareImage(imageUri, text)
                }
            } catch (e: Exception) {
                Toast.makeText(activityPar, "Falha ao salvar imagem!", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activityPar!!.requestPermissions(permissoes, 1)
            }
        }
    }

    //Older versions P
    private fun storeImageOldVersions(bitmap: Bitmap?) {
        if (ContextCompat.checkSelfPermission(
                activityPar!!.applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activityPar!!.applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            val now = Date()
            DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
            val root = Environment.getExternalStorageDirectory().toString()
            val myDir = File("$root/${folderNamePar.toString().lowercase().replace(" ", "_")}")
            myDir.mkdirs()

            val fname = "Image-${System.currentTimeMillis()}.jpg"
            val file = File(myDir, fname)
            file.mkdirs()
            try {
                val out = FileOutputStream(file)
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()

                Toast.makeText(activityPar, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            if (shareImagePar == true) {
                shareImage(Uri.parse(file.path), fname)
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activityPar!!.requestPermissions(permissoes, 1)
            }
        }
    }

    private fun shareImage(imageUri: Uri?, text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhando imagem $text")
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)

        try {
            activityPar!!.startActivity(Intent.createChooser(shareIntent, "Compartilhar"))
            Toast.makeText(activityPar, "Compartilhando...", Toast.LENGTH_SHORT)
                .show()
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activityPar, "Nenhum aplicativo disponível", Toast.LENGTH_SHORT)
                .show()
        }
    }
}