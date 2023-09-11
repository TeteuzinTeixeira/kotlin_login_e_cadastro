package com.example.projetofirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetofirebase.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.buttonCadastro.setOnClickListener{
            val cadastroNome = binding.editNome.text.toString()
            val cadastroEmail = binding.editEmail.text.toString()
            val cadastroSenha = binding.editSenha.text.toString()
            signunUpDatabase(cadastroNome, cadastroEmail, cadastroSenha)
        }

        binding.btVoltar.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

    }

    private fun signunUpDatabase(nome: String, email: String, senha: String){
        val insertedRowId = databaseHelper.insertUser(nome, email, senha)
        if (insertedRowId != -1L){
            Toast.makeText(this, "Cadstro Efetuado com Sucesso", Toast.LENGTH_SHORT).show()
            val intentLogin = Intent(this, FormLogin::class.java)
            startActivity(intentLogin)
            finish()
        }
        else {
            Toast.makeText(this,"Erro ao efetuar cadastro", Toast.LENGTH_SHORT).show()
        }
    }

}