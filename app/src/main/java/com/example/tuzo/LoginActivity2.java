package com.example.tuzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity2 extends AppCompatActivity {

    Button regis;
    EditText nomb, corr, chof, pass;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mFirestore=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        nomb = findViewById(R.id.nomb);
        corr = findViewById(R.id.corr);
        chof = findViewById(R.id.chof);
        pass = findViewById(R.id.pass);

        TextView regis = (TextView) findViewById(R.id.regis);

        regis = findViewById(R.id.regis);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombus = nomb.getText().toString().trim();
                String corrus = corr.getText().toString().trim();
                String chofus = chof.getText().toString().trim();
                String passus = pass.getText().toString().trim();

                if (nombus.isEmpty() && corrus.isEmpty() && chofus.isEmpty() && passus.isEmpty()){
                    Toast.makeText(LoginActivity2.this, "COMPLETA LOS DATOS ", Toast.LENGTH_SHORT).show();

                }else{
                    register(nombus, corrus, chofus, passus);

                }

            }
        });





    }

    private void register(String nombus, String corrus, String chofus, String passus) {
        mAuth.createUserWithEmailAndPassword(corrus, passus).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = FirebaseAuth.getInstance().getUid();

                Map<String, Object> map = new HashMap<>();
                map.put("id",id);
                map.put("nombre",nombus);
                map.put("correo",corrus);
                map.put("chofer",chofus);
                map.put("password",passus);

                mFirestore.collection("Users").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                        Toast.makeText(LoginActivity2.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity2.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity2.this, "Error al Registrar", Toast.LENGTH_SHORT).show();
            }
        });



    }
}