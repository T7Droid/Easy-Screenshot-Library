## An easy to use Library that will help you to take screenshots of the views in yout app



**Step 1.** Add the JitPack repository to your build file:



Add it in your root build.gradle at the end of repositories:

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```css
	dependencies {
	        implementation 'com.github.T7Droid:Easy-Screenshot-Library:Tag'
	}
```



**Step 3.** Add the READ and WRITE permissions on Manifest file:



```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```



Now this is ready for use!

```
val myTxtView = findViewById<View>(R.id.rootView)

//In you activity or fragment, intantiate an EasyScreenshot object and pass the //parameters you want to it's Builder:

val screenshot: EasyScreenshot = EasyScreenshot.Builder()
    .activity(this)
    .folderName("T7Droid Images") //Nome da pasta que será criada na galeria
    .pathInExternalStorage("screenshots") //Nome da imagem
    .targetViewId(myTxtView) //Any View you want to take the screenshot
    .shareAfterScreenshot(true) //se você deseja compartilhar após tirar o screenshot
    .build()

screenshot.takeScreenshot()
```



The parameters are:



activity( activity: Activity) - The Activity context, you can use "this" if you are in an Activity, or, if you are inside a Fragment you can use activity, requireActivity, ... in Koltin, and getActivity( ) in Java;

folderName(folder: String ) - Here you can define the name of the new folder that will be created in the Galery app;

pathInExternalStorage( ) - For the version before API 29 (Q) it will be the name of the folder that will be created to store the screenshots images, for the version above (API 29 Q+) which uses the Scope Storage, this will be the name  of the image saved.

targetViewId(view: View) - Here you will pass the view you want to save as an image in the JPEG format, if you want to take a screenshot all the screen (not including ActionBar neither StatusBar) you need only to define an id for the root view of your Activity or Fragment and pass it as a parameter in this method;

shareAfterScreenshot(share: Boolean) - Set true if you want to imediatelly share the image after taking the screenshot, if you want only to save the image and do not want to share set it as false.

