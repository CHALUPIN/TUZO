package com.example.tuzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.HashMap;
import java.util.Map;

public class Prueba1 extends AppCompatActivity {

    Button sig1;
        EditText id_nombre, id_correo, id_confirm, id_chofer;

        private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba1);

        this.setTitle("Prueba 1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();


        id_nombre = findViewById(R.id.id_nombre);
        id_correo = findViewById(R.id.id_correo);
        id_confirm = findViewById(R.id.id_confirm);
        id_chofer = findViewById(R.id.id_chofer);

        TextView sig1 = (TextView) findViewById(R.id.sig1);

        sig1 = findViewById(R.id.sig1);

        sig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombus = id_nombre.getText().toString().trim();
                String corrus = id_correo.getText().toString().trim();
                String confus = id_confirm.getText().toString().trim();
                String chofus = id_chofer.getText().toString().trim();

                if (nombus.isEmpty() && corrus.isEmpty() && confus.isEmpty() && chofus.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();

                }else{
                    postUsu(nombus, corrus, confus, chofus);

                }

            }
        });
    }

    private void postUsu(String nombus, String corrus, String confus, String chofus) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nombus);
        map.put("correo", corrus);
        map.put("confirmacion", confus);
        map.put("chofer", chofus);

        mfirestore.collection("Usuario").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}