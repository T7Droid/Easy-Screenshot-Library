package com.thyagoneves.model

import android.app.Activity
import android.view.View
import com.thyagoneves.Helper
import com.thyagoneves.Type

class EasyViewScreenshot private constructor(
    private val path: String?,
    private val folderName: String?,
    private val type: Type? = null,
    private val viewId: View? = null,
    private var share: Boolean?,
    private val activity: Activity?
) {

     data class Builder(
         private var imageName: String? = null,
         private var folderName: String? = null,
         private var type: Type? = null,
         private var viewId: View? = null,
         private var shareImagePar: Boolean? = null,
         private var activityPar: Activity? = null
    ) {
        fun imageFileName(imageName: String) = apply { this.imageName = imageName }
        fun folderName(folderName: String) = apply { this.folderName = folderName }
        fun targetViewId(type: Type) = apply { this.type = type!!}
        fun targetViewId(viewId: View) = apply { this.viewId = viewId!! }
        fun shareAfterScreenshot(shareImage: Boolean) = apply { this.shareImagePar = shareImage }
        fun activity(activity: Activity) = apply { this.activityPar = activity }
        fun build() = EasyViewScreenshot(imageName, folderName, type, viewId, shareImagePar, activityPar)
    }

    fun takeScreenshot(){

        val helper = Helper()
        if (viewId != null) {
           helper.takeScreenshotAndSaveImage(path + System.currentTimeMillis(), folderName, viewId ,share, activity)
        }

        if (type == Type.FULLSCREEN_WITH_ACTION_BAR) {
                val view = activity?.window?.decorView
                helper.takeScreenshotAndSaveImage(path + System.currentTimeMillis(), folderName, view, share, activity)
        }
    }
}