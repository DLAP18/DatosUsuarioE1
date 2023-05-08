package com.example.datosusuarioe1

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.datosusuarioe1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var persona = Persona(null, null, null, null, null, null)
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

        //Despliega el date picker dando clic en la imagen del calendario o en el edit text
        binding.etFechaNacimiento.setOnClickListener{showDatePickerDialog()}
        binding.imgCalendario.setOnClickListener{showDatePickerDialog()}
    }

    fun clickBoton(view: View){
        val intent = Intent(this, MainActivity2::class.java)
        val bundle = Bundle()

        val nombre = binding.etNombre.text.toString()
        val apellidos = binding.etApellidos.text.toString()
        val fechaN = binding.etFechaNacimiento.text.toString()
        val correo = binding.etEmail.text.toString()
        val noCuenta = binding.etNumerocuenta.text.toString()
        val carrera = binding.scarreras.selectedItem.toString()

        persona = Persona(nombre, apellidos, fechaN, correo, noCuenta, carrera)
        bundle.putParcelable("persona", persona)

        if(validarCampos()){
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    fun validarNombre(nombre: String): Boolean{
        val re = Regex("[A-Za-z ]*")
        return nombre.matches(re)
    }

    private fun isValidEmail(mail: CharSequence) =
        (!TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches())

    fun validarNoCuenta(noCuenta: String): Boolean{
        if(noCuenta.length != 9){
            return false
        }
        return true
    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment {day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int){
        val dayC = resources.getString(R.string.diaC, day.toString())
        val monthC = month + 1
        val monthCo = resources.getString(R.string.mesC, monthC.toString())

        if(day <= 9 && monthC <= 9){
            binding.etFechaNacimiento.setText(resources.getString(R.string.fechaN, dayC, monthCo, year))
        }else if(monthC <= 9){
            binding.etFechaNacimiento.setText(resources.getString(R.string.fechaN, day.toString(), monthCo, year))
        }else if(day <= 9){
            binding.etFechaNacimiento.setText(resources.getString(R.string.fechaN, dayC, monthC.toString(), year))
        }else{
            binding.etFechaNacimiento.setText(resources.getString(R.string.fechaN, day.toString(), monthC.toString(), year))
        }
    }

    //Función que válida los campos vacíos
    fun validarCampos(): Boolean{
        var esValido = true

        if(binding.etNombre.text.isEmpty()){
            binding.etNombre.error = resources.getString(R.string.valorRequerido)
            esValido = false
        }else if(!validarNombre(binding.etNombre.text.toString())){
            binding.etNombre.error = resources.getString(R.string.informacionNoValida)
            esValido = false
        }else{
            binding.etNombre.error = null
        }

        if(binding.etApellidos.text.isEmpty()) {
            binding.etApellidos.error = resources.getString(R.string.valorRequerido)
            esValido = false
        }else if(!validarNombre(binding.etApellidos.text.toString())){
            binding.etApellidos.error = resources.getString(R.string.informacionNoValida)
            esValido = false
        }else{
            binding.etApellidos.error = null
        }

        if(binding.etFechaNacimiento.text.isEmpty()) {
            binding.etFechaNacimiento.error = resources.getString(R.string.valorRequerido)
            esValido = false
        }else{
            binding.etFechaNacimiento.error = null
        }

        if(binding.etEmail.text.isEmpty()) {
            binding.etEmail.error = resources.getString(R.string.valorRequerido)
            esValido = false
        }else if(!isValidEmail(binding.etEmail.text.toString())){
            binding.etEmail.error = resources.getString(R.string.informacionNoValida)
            esValido = false
        }else{
            binding.etEmail.error = null
        }

        if(binding.etNumerocuenta.text.isEmpty()) {
            binding.etNumerocuenta.error = resources.getString(R.string.valorRequerido)
            esValido = false
        }else if(!validarNoCuenta(binding.etNumerocuenta.text.toString())){
            binding.etNumerocuenta.error = resources.getString(R.string.informacionNoValida)
            esValido = false
        }else{
            binding.etNumerocuenta.error = null
        }

        if(binding.scarreras.selectedItemPosition == 0) {
            Toast.makeText(this, R.string.seleccionaOpcion, Toast.LENGTH_SHORT).show()
            esValido = false
        }

        return esValido
    }
}