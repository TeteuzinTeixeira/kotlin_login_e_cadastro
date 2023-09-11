package com.example.projetofirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetofirebase.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btEntrar.setOnClickListener{
            val loginEmail = binding.editEmail.text.toString()
            val loginSenha = binding.editSenha.text.toString()
            LoginDatabase(loginEmail, loginSenha)
        }

        binding.textTelaCadastro.setOnClickListener {
            val intentCadastro = Intent(this, FormCadastro::class.java)

            startActivity(intentCadastro)
        }
    }
        private fun LoginDatabase(email: String, senha: String){
            val userExists = databaseHelper.readUser(email, senha)
            if (userExists){
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                val intentSucesso = Intent(this, TelaPrincipal::class.java)
                startActivity(intentSucesso)
                finish()
            }else {
                Toast.makeText(this, "email ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }


}