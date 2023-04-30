package com.example.datosusuarioe1

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Persona (var nombre: String?, var apellidos: String?, var fechaNacimiento: String?, var correoE: String?, var noCuenta: Int?, var carrera: String?): Parcelable