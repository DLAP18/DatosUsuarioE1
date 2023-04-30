package com.example.datosusuarioe1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.datosusuarioe1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.carreras,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.scarreras.adapter = adapter
        }

        //binding.scarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //override fun onItemSelected(
                //parent: AdapterView
            //)
        //}
    }

    fun clickBoton(view: View){
        val intent = Intent(this, MainActivity2::class.java)

        val bundle = Bundle()

        val persona = Persona("Juana", "Banana", "10/03/22", "lala@hotmail.com", 123456789, "Computación")
        bundle.putParcelable("persona", persona)

        intent.putExtras(bundle)

        startActivity(intent)

        /*if (binding.scarreras.selectedItemPosition == 0){
            Toast.makeText(this,
            "Por favor, seleccione una opción",
            Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this,
            "La opción seleccionada es el número ${binding.scarreras.selectedItemPosition}",
            Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun isValidEmail(mail: CharSequence) =
        (!TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches())
}