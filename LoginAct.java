package com.example.testakitsakifirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAct extends AppCompatActivity {
    EditText mailtxt,passwordtxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mailtxt = findViewById(R.id.mailtxt);
        passwordtxt = findViewById(R.id.passwordtxt);
    }

    public void loginUser(View v){
        String mail = mailtxt.getText().toString().trim();
        String password = passwordtxt.getText().toString().trim();
        Toast.makeText(this, mail+password, Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginAct.this, "OK", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginAct.this, "Eero", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void goToRegister(View v){

    }
}