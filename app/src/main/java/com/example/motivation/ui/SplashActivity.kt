package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.Infra.MotivationConstatants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)


        buttonSave.setOnClickListener(this)

        verifyName()

    }


    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.buttonSave) {
            handleSave()
        }
    }


    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstatants.KEY.PERSON_NAME)

        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstatants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
        }
    }

}