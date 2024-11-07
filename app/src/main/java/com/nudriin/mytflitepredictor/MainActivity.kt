package com.nudriin.mytflitepredictor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nudriin.mytflitepredictor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var predictorHelper: PredictorHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        predictorHelper = PredictorHelper(
            context = this,
            onResult = {result ->
                binding.tvResult.text = result
            },
            onError = {errorMessage ->
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        )

        binding.btnPredict.setOnClickListener {
            val input = binding.edSales.text.toString()
            predictorHelper.predict(input)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        predictorHelper.close()
    }


}