package com.example.datosusuarioe1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datosusuarioe1.databinding.ActivityMain2Binding

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
                //Toast.makeText(this, "El nombre del objeto parcelable es: ${persona.nombre}, y su cuenta es: ${persona.apellidos}", Toast.LENGTH_LONG).show()
                binding.tvCarrera.text = persona.carrera
                binding.tvNombreCompleto.text = resources.getString(R.string.nombreCompleto, persona.nombre, persona.apellidos)
                binding.tvEdad.text = persona.fechaNacimiento
                binding.tvSignoZodiacal.text = persona.fechaNacimiento
                binding.tvHoroscopoChino.text = persona.fechaNacimiento
                binding.tvCorreoValido.text = persona.correoE
                binding.tvNumeroCuenta.text = persona.noCuenta
            }
        }
    }
}