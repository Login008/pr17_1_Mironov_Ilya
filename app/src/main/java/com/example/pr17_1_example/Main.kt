package com.example.pr17_1_example

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;


class Main : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var pass: EditText
    private lateinit var pref: SharedPreferences
    private lateinit var textLog: TextView
    private lateinit var textPass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login = findViewById<EditText>(R.id.login)
        pass = findViewById<EditText>(R.id.pass)
        textLog = findViewById<TextView>(R.id.textViewLog)
        textPass = findViewById<TextView>(R.id.textViewPass)
    }

    fun handler(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Что делаем?")
            .setMessage("Выбери, что делать")
            .setCancelable(true)
            .setPositiveButton("Сохранить") { _, _ ->

                Toast.makeText(this, "Сохранено", Toast.LENGTH_LONG).show()
                pref = getPreferences(MODE_PRIVATE)
                val ed = pref.edit()
                ed.putString("login", login.getText().toString())
                ed.putString("password", pass.getText().toString())
                ed.apply()
                textLog.text = pref.getString("login", "")
                textPass.text = pref.getString("password", "")
            }
            .setNegativeButton("Загрузить") { _, _ ->
                Toast.makeText(this, "Загружено", Toast.LENGTH_LONG).show()
                pref = getPreferences(MODE_PRIVATE)
                login.setText(pref.getString("login", ""))
                pass.setText(pref.getString("password", ""))
                textLog.text = ""
                textPass.text = ""
            }
        builder.create()
        builder.show()
    }
}