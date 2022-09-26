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
