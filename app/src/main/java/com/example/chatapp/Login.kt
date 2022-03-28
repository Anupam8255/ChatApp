package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var login_btn:Button
    private lateinit var signUp_btn:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth=FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.edt_email)
        edtpassword=findViewById(R.id.edt_password)
        login_btn=findViewById(R.id.login_btn)
        signUp_btn=findViewById(R.id.signup_btn)
        signUp_btn.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        login_btn.setOnClickListener{
            val email = edtEmail.text.toString()
            val password =edtpassword.text.toString()
            login(email,password);
        }
    }
    private fun login(email:String,password:String){
        //code for logging in
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login,"User Does not Exist",Toast.LENGTH_SHORT).show()

                }
            }
    }
}