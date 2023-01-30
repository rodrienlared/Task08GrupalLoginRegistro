package com.example.task08grupalloginregistro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.task08grupalloginregistro.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var users = HashMap<String, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botonLogin()
        botonRegistro()

    }

    fun botonLogin(){
        binding.login.setImageResource(R.drawable.login)
        binding.login.setOnClickListener {
            if(areAllFieldsEmpty())
                alertaAllFieldsEmpty()
            else{
                obtenerUser()
                if (users.isNotEmpty())
                    checkUser()
                else
                    alertaUsuario()
            }
        }
    }

    private fun areAllFieldsEmpty(): Boolean {
        return binding.editTextNombre.text.toString().isEmpty() || binding.editTextTextPassword.text.toString().isEmpty()
    }

    fun botonRegistro(){
        binding.buttonRegistro.setImageResource(R.drawable.registro)
        binding.buttonRegistro.setOnClickListener {
            val intent= Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    fun checkUser(){
        if (users.containsKey(binding.editTextNombre.text.toString()))
            if (isValidUserToLogin()) {
                val intent = Intent(this, Home::class.java)
                intent.putExtra("USUARIO", binding.editTextNombre.text.toString())
                startActivity(intent)
            } else
                alertaPass()
        else
            alertaUsuario()
    }

    private fun isValidUserToLogin(): Boolean {
        return users.keys.first() == binding.editTextNombre.text.toString() && users[binding.editTextNombre.text.toString()].equals(binding.editTextTextPassword.text.toString()) && binding.editTextNombre.text.toString() == binding.editTextTextPassword.text.toString()
    }

    fun alertaUsuario(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario introducido no existe.")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Aceptar", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun alertaAllFieldsEmpty() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario y la contraseña no pueden estar vacíos.")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Aceptar", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun obtenerUser(){
        val user = intent.getStringExtra("USER")
        val pass = intent.getStringExtra("PASS")

        if (user != null && pass != null)
            users[user] = pass

    }

    fun alertaPass(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("La contraseña no es correcta para el usuario \"${binding.editTextNombre.text}\" o bien no cumple los requerimientos del ejercicio.")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Aceptar", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}