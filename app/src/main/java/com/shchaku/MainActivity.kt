package com.shchaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var binding: ActivityManagerQrBinding
//
        setContentView(savedInstanceState)
        binding = ActivityManagerQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScan.setOnClickListener { initScan() }
        setContentView(R.layout.activity_main)
    }

            private fun setContentView(savedInstanceState: Bundle?) {
            }

            private fun initScan() {
                val integrator = IntentIntegrator(this)
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                integrator.setPrompt("version inicial")
                integrator.setBeepEnabled(true)
                integrator.initiateScan()
            }

            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
                if (result != null) {
                    if (result.contents == null) {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this,
                            "El contenido del qr es: ${result.contents}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data)
                }
            }
}