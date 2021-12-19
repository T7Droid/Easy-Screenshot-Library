package com.thyagoneves.easy_screenshot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myTxtView = findViewById<View>(R.id.rootView)

        val screenshot = Screenshot.Builder()
            .activity(this)
            .folderName("Thyago Neves") //Nome da pasta que será criada na galeria
            .pathInExternalStorage("screenshots") //Nome da imagem
            .targetViewId(myTxtView) //View que você deseja salvar a imagem
            .shareAfterScreenshot(true) //se você deseja compartilhar após tirar o screenshot
            .build()

        screenshot.takeScreenshot()
    }
}