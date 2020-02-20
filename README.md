[![](https://jitpack.io/v/plugxr/plugxr-vuforia-library.svg)](https://jitpack.io/#plugxr/plugxr-vuforia-library)


# plugxr-vuforia-library

Use this library to integrate into your app

#### Implement this library in build.gardle file

`implementation 'com.github.plugxr:plugxr-vuforia-library:0.1'`

#### Add this lines in build.gradle


allprojects {

    repositories {
    
        google()
        
        jcenter()
        
        maven { url 'https://jitpack.io' }
        
    }
  
}


#### Library implementation in java class


1. Create PlugXRGo Object.

    `PlugxrGo plugxrGo = new PlugxrGo();`

2. Initialise plugxrgo vuforia library 

    `plugxrGo.init(MainActivity.this);`

3. Replace with your vuforia generated 3 License keys

    `plugxrGo.putKeys(LicenceKeyToUnity,AccessKeyToUnity,SecretKeyToUnity);`

4. Start PlugxrGo.Call this line where you start AR . 

    `plugxrGo.start();`
