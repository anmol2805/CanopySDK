package com.canopydevelopers.canopysdk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.canopydevelopers.canopyauth.CanopyAuthCallback
import com.canopydevelopers.canopyauth.CanopyLogin




class MainActivity : AppCompatActivity() {
    private lateinit var canopyAuthCallback:CanopyAuthCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        canopyLogin.generate_token("b516008","anmol@2805","http://14.139.198.171:8080/token/generate-token")
    }
}
