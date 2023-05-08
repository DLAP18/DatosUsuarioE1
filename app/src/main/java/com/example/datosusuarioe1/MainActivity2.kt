package com.example.datosusuarioe1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datosusuarioe1.databinding.ActivityMain2Binding
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle =intent.extras

        if(bundle != null){
            var persona: Persona? = null

            //Pasando los datos instanciando un objeto
            persona = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                bundle.getParcelable("persona", Persona::class.java)
            } else {
                bundle.getParcelable("persona")
            }

            if(persona!=null){
                binding.tvCarrera.text = persona.carrera
                binding.tvNombreCompleto.text = resources.getString(R.string.nombreCompleto, persona.nombre, persona.apellidos)
                binding.tvEdad.text = resources.getString(R.string.edadR, edad(persona.fechaNacimiento.toString()))
                binding.tvSignoZodiacal.text = persona.fechaNacimiento
                binding.tvHoroscopoChino.text = persona.fechaNacimiento
                binding.tvCorreoValido.text = persona.correoE
                binding.tvNumeroCuenta.text = persona.noCuenta
            }
        }
    }

    fun edad(fechaN: String): String{
        val day = fechaN.subSequence(0,2)
        val month = fechaN.subSequence(3,5)
        val year = fechaN.subSequence(fechaN.length-4,fechaN.length)

        val dayN = day.toString().toInt()
        val monthN = month.toString().toInt()
        val yearN = year.toString().toInt()

        val fechaActual = obtenerFecha()
        var edad = 0

        if(monthN > fechaActual[1]) {
            edad = fechaActual[2] - yearN - 1
        }else{
            if(dayN < fechaActual[0] && monthN == fechaActual[1]) {
                edad = fechaActual[2] - yearN - 1
            }else if(dayN > fechaActual[0] && monthN == fechaActual[1]){
                edad = fechaActual[2] - yearN - 1
            }else{
                edad = fechaActual[2] - yearN
            }
        }

        return edad.toString()
    }

    fun obtenerFecha(): Array<Int> {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        //val date = c.time
        return arrayOf(day, month, year)
    }
}