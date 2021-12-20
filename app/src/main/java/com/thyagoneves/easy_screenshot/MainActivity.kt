package com.thyagoneves.easy_screenshot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thyagoneves.EasyViewScreenshot

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myTxtView = findViewById<android.view.View>(R.id.rootView)
        val btn = findViewById<android.view.View>(R.id.button)

        btn.setOnClickListener {
            val screenshot = EasyViewScreenshot.Builder()
                .activity(this)
                .folderName("Nova Pasta") //Nome da pasta que será criada na galeria
                .imageFileName("screenshot") //Nome da imagem
                .targetViewId(myTxtView) //View que você deseja salvar a imagem
                .shareAfterScreenshot(true) //se você deseja compartilhar após tirar o screenshot
                .build()
            screenshot.takeScreenshot()
        }
    }
}