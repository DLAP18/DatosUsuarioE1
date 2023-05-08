package com.example.datosusuarioe1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
                imagenCarrera(persona.carrera.toString())
                binding.tvNombreCompleto.text = resources.getString(R.string.nombreCompleto, persona.nombre, persona.apellidos)
                binding.tvEdad.text = resources.getString(R.string.edadR, edad(persona.fechaNacimiento.toString()))
                binding.tvSignoZodiacal.text = obtenerSignoZodiacal(persona.fechaNacimiento.toString())
                binding.tvHoroscopoChino.text = obtenerHoroscopoChino(persona.fechaNacimiento.toString())
                binding.tvCorreoValido.text = persona.correoE
                binding.tvNumeroCuenta.text = persona.noCuenta
            }
        }
    }

    override fun onBackPressed() {}

    fun botonRegreso(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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

    fun obtenerHoroscopoChino(fechaN: String): String{
        val rata = arrayOf(1900, 1912, 1924, 1936, 1948, 1960, 1972, 1984, 1996, 2008, 2020)
        val buey = arrayOf(1901, 1913, 1925, 1937, 1949, 1961, 1973, 1985, 1997, 2009, 2021)
        val tigre = arrayOf(1902, 1914, 1926, 1938, 1950, 1962, 1974, 1986, 1998, 2010, 2022)
        val conejo = arrayOf(1903, 1915, 1927, 1939, 1951, 1963, 1975, 1987, 1999, 2011, 2023)
        val dragon = arrayOf(1904, 1916, 1928, 1940, 1952, 1964, 1976, 1988, 2000, 2012, 2024)
        val serpiente = arrayOf(1905, 1917, 1929, 1941, 1953, 1965, 1977, 1989, 2001, 2013, 2025)
        val caballo = arrayOf(1906, 1918, 1930, 1942, 1954, 1966, 1978, 1990, 2002, 2014, 2026)
        val cabra = arrayOf(1907, 1919, 1931, 1943, 1955, 1967, 1979, 1991, 2003, 2015, 2027)
        val mono = arrayOf(1908, 1920, 1932, 1944, 1956, 1968, 1980, 1992, 2004, 2016, 2028)
        val gallo = arrayOf(1909, 1921, 1933, 1945, 1957, 1969, 1981, 1993, 2005, 2017, 2029)
        val perro = arrayOf(1910, 1922, 1934, 1946, 1958, 1970, 1982, 1994, 2006, 2018, 2030)
        val jabali = arrayOf(1911, 1923, 1935, 1947, 1959, 1971, 1983, 1995, 2007, 2019, 2031)

        val year = fechaN.subSequence(fechaN.length-4,fechaN.length)
        val yearN = year.toString().toInt()

        if(rata.contains(yearN)){
            return resources.getString(R.string.hcRata)
        }else if(buey.contains(yearN)){
            return resources.getString(R.string.hcBuey)
        }else if(tigre.contains(yearN)){
            return resources.getString(R.string.hcTigre)
        }else if(conejo.contains(yearN)){
            return resources.getString(R.string.hcConejo)
        }else if(dragon.contains(yearN)){
            return resources.getString(R.string.hcDragon)
        }else if(serpiente.contains(yearN)){
            return resources.getString(R.string.hcSerpiente)
        }else if(caballo.contains(yearN)){
            return resources.getString(R.string.hcCaballo)
        }else if(cabra.contains(yearN)){
            return resources.getString(R.string.hcCabra)
        }else if(mono.contains(yearN)){
            return resources.getString(R.string.hcMono)
        }else if(gallo.contains(yearN)){
            return resources.getString(R.string.hcGallo)
        }else if(perro.contains(yearN)){
            return resources.getString(R.string.hcPerro)
        }else if(jabali.contains(yearN)){
            return resources.getString(R.string.hcJabali)
        }

        return ""
    }

    fun obtenerSignoZodiacal(fechaN: String): String{
        /*Aries: 21 de marzo al 19 de abril
        Tauro: 20 de abril y al 20 de mayo
        Géminis: 21 de mayo al 20 de junio
        Cáncer:  21 de junio al 22 de julio
        Leo: 23 de julio al 22 de agosto
        Virgo: 23 de agosto al 22 de septiembre
        Libra: 23 de septiembre al 22 de octubre
        Escorpio: 23 de octubre al 21 de noviembre
        Sagitario: 22 de noviembre al 21 de diciembre
        Capricornio: 22 de diciembre al 19 de enero
        Acuario: 20 de enero al 18 de febrero
        Piscis: 19 de febrero al 20 de marzo*/

        val day = fechaN.subSequence(0,2)
        val month = fechaN.subSequence(3,5)

        val dayN = day.toString().toInt()
        val monthN = month.toString().toInt()

        if(monthN == 1){
            if(dayN <= 19){
                return resources.getString(R.string.szCapricornio)
            }else{
                return resources.getString(R.string.szAcuario)
            }
        }else if(monthN == 2){
            if(dayN <= 18){
                return resources.getString(R.string.szAcuario)
            }else{
                return resources.getString(R.string.szPiscis)
            }
        }else if(monthN == 3){
            if(dayN <= 20){
                return resources.getString(R.string.szPiscis)
            }else{
                return resources.getString(R.string.szAries)
            }
        }else if(monthN == 4){
            if(dayN <= 19){
                return resources.getString(R.string.szAries)
            }else{
                return resources.getString(R.string.szTauro)
            }
        }else if(monthN == 5){
            if(dayN <= 20){
                return resources.getString(R.string.szTauro)
            }else{
                return resources.getString(R.string.szGeminis)
            }
        }else if(monthN == 6){
            if(dayN <= 20){
                return resources.getString(R.string.szGeminis)
            }else{
                return resources.getString(R.string.szCancer)
            }
        }else if(monthN == 7){
            if(dayN <= 22){
                return resources.getString(R.string.szCancer)
            }else{
                return resources.getString(R.string.szLeo)
            }
        }else if(monthN == 8){
            if(dayN <= 22){
                return resources.getString(R.string.szLeo)
            }else{
                return resources.getString(R.string.szVirgo)
            }
        }else if(monthN == 9){
            if(dayN <= 22){
                return resources.getString(R.string.szVirgo)
            }else{
                return resources.getString(R.string.szLibra)
            }
        }else if(monthN == 10){
            if(dayN <= 22){
                return resources.getString(R.string.szLibra)
            }else{
                return resources.getString(R.string.szEscorpio)
            }
        }else if(monthN == 11){
            if(dayN <= 21){
                return resources.getString(R.string.szEscorpio)
            }else{
                return resources.getString(R.string.szSagitario)
            }
        }else if(monthN == 12){
            if(dayN <= 21){
                return resources.getString(R.string.szSagitario)
            }else{
                return resources.getString(R.string.szCapricornio)
            }
        }

        return ""
    }

    fun imagenCarrera(carrera: String){
        val carreras = resources.getStringArray(R.array.carreras)
        val img = binding.ivCarrera

        if(carrera == carreras[1]){
            img.setImageResource(R.drawable.aeroespacial)
        }else if(carrera == carreras[2]){
            img.setImageResource(R.drawable.civil)
        }else if(carrera == carreras[3]){
            img.setImageResource(R.drawable.geomatica)
        }else if(carrera == carreras[4]){
            img.setImageResource(R.drawable.ambiental)
        }else if(carrera == carreras[5]){
            img.setImageResource(R.drawable.geofisica)
        }else if(carrera == carreras[6]){
            img.setImageResource(R.drawable.geologica)
        }else if(carrera == carreras[7]){
            img.setImageResource(R.drawable.petrolera)
        }else if(carrera == carreras[8]){
            img.setImageResource(R.drawable.minasmetalurgia)
        }else if(carrera == carreras[9]){
            img.setImageResource(R.drawable.computacion)
        }else if(carrera == carreras[10]){
            img.setImageResource(R.drawable.electricaelectronica)
        }else if(carrera == carreras[11]){
            img.setImageResource(R.drawable.telecomunicaciones)
        }else if(carrera == carreras[12]){
            img.setImageResource(R.drawable.mecanica)
        }else if(carrera == carreras[13]){
            img.setImageResource(R.drawable.industrial)
        }else if(carrera == carreras[14]){
            img.setImageResource(R.drawable.mecatronica)
        }else if(carrera == carreras[15]){
            img.setImageResource(R.drawable.biomedicos)
        }
    }
}