# Canopy-sdk

###### *Easier and faster access to softwares of IIIT without directly accessing its APIs using this sdk*

### Getting Started
1.Add this in your root build.gradle at the end of repositories:

 ``` gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
  
 2.Add permissions in your manifest file:
 
  ```xml
<permission android:name="android.permission.INTERNET" />       
```

## Canopy-Authentication

###### Firebase like authentication system containing hibiscus login using authentication token

 3.Add these dependencies in your module app build.gradle:
 
  ``` gradle
dependencies {
    implementation 'com.github.anmol2805:CanopySDK:0.2.0'
 }
 ```
 
### Usage

 ```kotlin
canopyAuthCallback = object : CanopyAuthCallback {
            override fun onLoginSuccess(loginresponse: Boolean?) {
                if (loginresponse!!){
                    System.out.println("loginstatus true")
                }
            }
            override fun onLoginFailure(loginerror: String?) {
                System.out.println(loginerror)
            }
        }
        val canopyLogin = CanopyLogin(canopyAuthCallback, this)
        canopyLogin.generate_token("******","******","http://14.139.198.171:8080/token/generate-token")
```

### For some devices operating on android P or API level 28(optional):

1.Create a new xml file in your *app->res->xml->network_security_config.xml*

Add this following code:

  ```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">14.139.198.171</domain>
    </domain-config>
</network-security-config>       
```

2.Now in your manifest file add this line:

  ```xml
<application>
    ...
    android:networkSecurityConfig="@xml/network_security_config"
</application>       
```
