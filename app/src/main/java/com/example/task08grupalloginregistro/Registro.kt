package com.example.task08grupalloginregistro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isEmpty
import com.example.task08grupalloginregistro.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validarUsuario()
        botonVolver()
        botonesSexo()

    }

    fun validarUsuario(){
        binding.registrar.setOnClickListener {
            if (binding.editTextNombre.text.isEmpty() || binding.editTextTextContraseA.text.isEmpty())
                alertaRegistro()
            else if (!binding.radioH.isChecked && !binding.radioM.isChecked)
                alertaRegistro()
            else if (binding.spinnerNacio.isEmpty())
                alertaRegistro()
            else if (!binding.checkBoxFutbol.isChecked && !binding.checkBoxTenis.isChecked)
                alertaRegistro()
            else if (binding.MultiLine.text.isEmpty())
                alertaRegistro()
            else{
                val intent = Intent(this, Login::class.java)
                intent.putExtra("USER", binding.editTextNombre.text.toString())
                intent.putExtra("PASS", binding.editTextTextContraseA.text.toString())
                startActivity(intent)
            }
        }
    }

    fun botonVolver(){
        binding.cancelar.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    fun alertaRegistro(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Tiene que introducir todos los datos necesarios.")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Aceptar", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun botonesSexo(){
        binding.radioH.setOnClickListener {
            binding.radioM.isChecked = false
        }
        binding.radioM.setOnClickListener {
            binding.radioH.isChecked = false
        }
    }
}