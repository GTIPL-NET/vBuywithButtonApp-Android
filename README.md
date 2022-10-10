# vBuywithButtonApp-Android

To integrate this library follow the steps :-  

# IN GRADLE

Step 1. Add the JitPack repository to your build file

    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
      
Step 2. Add the dependency

    dependencies {
	        implementation 'com.github.GTIPL-NET:vBuywithButtonApp-Android:Tag'
	}
  
  # IN MAVEN
  
  Step 1. Add the JitPack repository to your build file
  
  
     <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
    </repositories>
    
    
  Step 2. Add the dependency

      <dependency>
          <groupId>com.github.GTIPL-NET</groupId>
          <artifactId>vBuywithButtonApp-Android</artifactId>
          <version>Tag</version>
      </dependency>


# To Use Library 

Import
     
     import com.gtipl.meetadlink.MeetLinkModel
 
Declare 
 
     var meetLinkView: MeetLinkModel? = null
     
Defination

 	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            	AlertDialog.Builder(this)
		.setTitle("Permission Required..")
		.setMessage("Keep widget calls upfront while using other apps. Simply give permission to draw over other apps")
		.setPositiveButton("Grant Permission", { dialog, which ->

                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivityForResult(intent, 8888)
                })
                .show()
        } else {

            if (meetLinkView == null) {
                meetLinkView = MeetLinkModel(this, windowManager, "https://vbuywith.com/Customer/widget/20")
            }
            meetLinkView!!.showPopView()

        }
To hide pop up

        meetLinkView!!.hidePopView()

