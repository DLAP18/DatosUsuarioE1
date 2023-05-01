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
    var persona = Persona(null, null, null, null, 0, null)
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

        val nombre = binding.etNombre.text.toString()
        val apellidos = binding.etApellidos.text.toString()
        val correo = binding.etEmail.text.toString()
        val noCuenta = binding.etNumerocuenta.text.toString().toInt()
        val carrera = binding.scarreras.selectedItem.toString()

        //binding.etFechaNacimiento.text.isNotEmpty()
        if(binding.etNombre.text.isNotEmpty()){
            if(validarNombre(nombre)){
                if(binding.etApellidos.text.isNotEmpty()){
                    if(validarNombre(apellidos)){
                        if(binding.etEmail.text.isNotEmpty()){
                            if(isValidEmail(correo)){
                                if(binding.etNumerocuenta.text.isNotEmpty()){
                                    if(validarNoCuenta(noCuenta)){
                                        if(binding.scarreras.selectedItemPosition != 0){
                                            persona = Persona(nombre, apellidos, "10/03/1987", correo, noCuenta, carrera)
                                            bundle.putParcelable("persona", persona)
                                            //Toast.makeText(this, "La carrera es: ${persona.carrera}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

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

    fun validarNombre(nombre: String): Boolean{
        val re = Regex("[A-Za-z ]*")
        return nombre.matches(re)
    }
    private fun isValidEmail(mail: CharSequence) =
        (!TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches())

    fun validarNoCuenta(noCuenta: Int): Boolean{
        val nCuenta = noCuenta.toString()
        if(nCuenta.length != 9){
            return false
        }
        return true
    }
}