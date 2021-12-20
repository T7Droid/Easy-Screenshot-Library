package com.thyagoneves

import android.app.Activity
import android.view.View

class EasyViewScreenshot private constructor(
    private val path: String?,
    private val folderName: String?,
    private val view: View? = null,
    private var share: Boolean?,
    private val activity: Activity?
) {

     data class Builder(
         private var imageName: String? = null,
         private var folderName: String? = null,
         private var view: View? = null,
         private var shareImagePar: Boolean? = null,
         private var activityPar: Activity? = null
    ) {
        fun imageFileName(imageName: String) = apply { this.imageName = imageName }
        fun folderName(folderName: String) = apply { this.folderName = folderName }
        fun targetViewId(view: View) = apply { this.view = view}
        fun shareAfterScreenshot(shareImage: Boolean) = apply { this.shareImagePar = shareImage }
        fun activity(activity: Activity) = apply { this.activityPar = activity }
        fun build() = EasyViewScreenshot(imageName, folderName, view, shareImagePar, activityPar)
    }

    fun takeScreenshot() {
        val helper = Helper()
        if (view != null) {
            helper.takeScreenshotAndSaveImage(path + System.currentTimeMillis(), folderName, view, share, activity)
        }
    }
}