package com.thyagoneves.easy_view_screenshot

import android.app.Activity
import android.view.View

class Screenshot private constructor(
    val path: String?,
    val folderName: String?,
    val view: View? = null,
    var share: Boolean?,
    val activity: Activity?
) {

    data class Builder(
        private var path: String? = null,
        private var folderName: String? = null,
        private var viewId: View? = null,
        private var shareImagePar: Boolean? = null,
        private var activityPar: Activity? = null
    ) {
        fun pathInExternalStorage(path: String) = apply { this.path = path }
        fun folderName(folderName: String) = apply { this.folderName = folderName }
        fun targetViewId(viewId: View) = apply { this.viewId = viewId}
        fun shareAfterScreenshot(shareImage: Boolean) = apply { this.shareImagePar = shareImage }
        fun activity(activity: Activity) = apply { this.activityPar = activity }
        fun build() = Screenshot(path, folderName, viewId, shareImagePar, activityPar)
    }

    fun takeScreenshot() {
        val helper = Helper()
        if (view != null) {
            helper.takeScreenshotAndSaveImage(path + System.currentTimeMillis(), folderName, view, share, activity)
        }
    }
}