package com.example.qrcodescanner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    lateinit var buttonScan:Button
    lateinit var textMessage:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textMessage = findViewById(R.id.textMessage)
        buttonScan = findViewById(R.id.buttonScan)
        buttonScan.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.setOrientationLocked(true)
            scanner.initiateScan()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
            if (result != null){
                if (result.contents != null){
                    textMessage.text = result.contents
                }
                else{
                    Toast.makeText(this,"Failed to scan",Toast.LENGTH_LONG).show()
                }
            }else{
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}