package com.example.datosusuarioe1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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
                Toast.makeText(this, "El nombre del objeto parcelable es: ${persona.nombre}, y su cuenta es: ${persona.apellidos}", Toast.LENGTH_LONG).show()
            }
        }
    }
}