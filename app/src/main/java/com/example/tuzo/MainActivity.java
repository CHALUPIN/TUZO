package com.example.tuzo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity<button> extends AppCompatActivity {

    button bt_aceptar;
    button Ingresar;
    EditText email, passwo;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boton de Logeo
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.In_correonum);
        passwo = findViewById(R.id.in_contra);

        TextView Ingresar = (TextView) findViewById(R.id.Ing);
        Ingresar = findViewById(R.id.Ing);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailuser = email.getText().toString().trim();
                String passuser = passwo.getText().toString().trim();
                if(emailuser.isEmpty() && passuser.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
                    
                    
                }else{

                    loginusuarios(emailuser, passuser);
                }

            }
        });




        //boton de registro
        TextView bt_aceptar = (TextView) findViewById(R.id.bt_aceptar);
        bt_aceptar = findViewById(R.id.bt_aceptar);
        bt_aceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity2.class));
            }
        });


    }

    private void loginusuarios(String emailuser, String passuser) {
        mAuth.signInWithEmailAndPassword(emailuser, passuser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, MapaActivity.class));
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });


    }
}