English 🇺🇸 - Português (pt-br) 🇧🇷



🇺🇸 _English:_

### An easy to use Library that will help you to take screenshots of the views in yout app



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









🇧🇷 _Português:_

### Uma Library fácil de usar que irá lhe permitir tirar screenshots das views no seu aplicativo, veja como usar:



**Passo 1.** Adicione o repositório do JitPack ao seu projeto.



Adicione ao seu arquivo raiz build.gradle  (Project), em repositories , ou no seu arquivo settings.gradle:

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** adicione a dependência

```css
	dependencies {
	        implementation 'com.github.T7Droid:Easy-Screenshot-Library:Tag'
	}
```



**Step 3.** Adicione as permissões de leitura e gravação ao arquivo Manifest:



```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```



## E pronto, agora você já pode usar!

 

Veja como nos comentários no código abaixo:

```

val myTxtView = findViewById<View>(R.id.rootView)
//Ou binding.myTextView se estiver usando o Viewbinding

//Na sua aActivity ou Fragment, instancie um objeto EasyScreenshot and passe
//os parâmetros que desejar no seu construtor "EasyScreenshot.Builder()":

val screenshot: EasyScreenshot = EasyScreenshot.Builder()
    .activity(this) // 1°- Activity
    .folderName("T7Droid Images") //2° - Nome para a pasta que será criada na galeria
    .pathInExternalStorage("screenshots") //3° - Nome da imagem que será salva
    .targetViewId(myTxtView) //4° - View ou ViewGroup que desejar tirar o screenshot
    .shareAfterScreenshot(true) //5° - Deseja compartilhar após tirar o screenshot?
    .build()

//Após criamos e instanciarmos nosso objeto Easycreenshot basta usá-lo :)
//Para isso basta chamar o método takeScreenshot()
screenshot.takeScreenshot()
```



Os métodos/parâmetros são:



- activity( activity: Activity) - A Activity contexto, você pode usar "this" se estiver em uma Activity, ou, se estiver dentro um Fragment poderá passar como parâmetro "activity, requireActivity, etc..." em Koltin, e "getActivity( )" em Java;

- folderName(folder: String ) - Para as versões anteriores ao Android 10  (API 29 ou Q) o nome que você passar como parâmetro será o nome da nova pasta que será criada para armazenar seus screenshots. Por outro lado, nas versões Android 10 (API 29 ou Q) e acima, que fazem uso do Scoped storage, esse será o nome da nova pasta que será criada na galeria do seu dispositivo;

 Alguns exemplos de nomes: "Comprovantes", "Recibos", ou até o nome do seu próprio aplicativo :D

- imageFileName( imageName: String) - Nome da imagem que será salva concatenado com a data em que foi tirada.

targetViewId(view: View) - Aqui você irá passar qualquer View ou ViewGroup que desejar salvar como uma imagens no formato JPEG, se você desejar tirar um screenshot de toda a tela (não incluindo nessa versão a ActionBar nem a StatusBar) você precisa apenas definir um id  para a View raiz do layoutyour da sua Activity ou Fragment e passar como parâmetro nesse método;

shareAfterScreenshot(share: Boolean) - Defina como true se desejarcompartilhar a imagem imediatamente após armazená-la, se você deseja apenas salvar a imagem e não deseja compartilhá-la, defina como false.



Tem alguma dúvida sobre a library? Encontrou algum problema ou tem sugestões de melhorias? Fique à vontade para me contatar.



## Contato

[www.t7droid@gmail.com](www.t7droid@gmail.com)



