package com.canopydevelopers.canopysdk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.canopydevelopers.canopyauth.CanopyLogin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val canopyLogin = CanopyLogin()
        canopyLogin.generate_token("b516008",
            "anmol@280",
            "http://14.139.198.171:8080/token/generate-token",
            this
        ) { error ->
            System.out.println(error)
        }
    }
}
