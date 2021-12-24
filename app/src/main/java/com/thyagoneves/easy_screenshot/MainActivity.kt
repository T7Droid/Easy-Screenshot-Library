package com.thyagoneves.easy_screenshot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thyagoneves.EasyViewScreenshot
import com.thyagoneves.Type
import com.thyagoneves.model.EasyViewScreenshot

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myTxtView = findViewById<android.view.View>(R.id.myTextView)
        val btnSingleView = findViewById<android.view.View>(R.id.btn_single_view)
        val btnFullScreenWithActionBar = findViewById<android.view.View>(R.id.btn_action_bar)

        //Tirando screenshot de uma View específica:

        btnSingleView.setOnClickListener {
            val screenshot = EasyViewScreenshot.Builder()
                .activity(this) //O contexto, uma Activity.
                .folderName(getString(R.string.folder_name)) //Nome da pasta que será criada na galeria
                .imageFileName(getString(R.string.image_name)) //Nome da imagem
                .targetViewId(myTxtView) //A View que você deseja tirar o screenshot
                .shareAfterScreenshot(true) //se você deseja compartilhar após tirar o screenshot
                .build()

            screenshot.takeScreenshot()

        }

        //Tirando screenshot da tela inteira:
        btnFullScreenWithActionBar.setOnClickListener {
            val screenshot = EasyViewScreenshot.Builder()
                .activity(this)
                .folderName(getString(R.string.folder_name))
                .imageFileName(getString(R.string.image_name))
                .targetViewId(Type.FULLSCREEN_WITH_ACTION_BAR) //Passe a constante "Type.FULLSCREEN_WITH_ACTION_BAR" para tirar um screenshot da tela inteira incluindo apenas a ActionBar.
                .shareAfterScreenshot(true)
                .build()

            screenshot.takeScreenshot()
        }
    }
}