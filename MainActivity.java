package com.example.testakitsakifirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nametxt,agetxt,mailtxt,passwordtxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        nametxt = findViewById(R.id.nametxt);
        agetxt = findViewById(R.id.agetxt);
        mailtxt = findViewById(R.id.mailtxt);
        passwordtxt = findViewById(R.id.passwordtxt);
    }

    public void registerUser(View v) {
        String name = nametxt.getText().toString().trim();
        String age = agetxt.getText().toString().trim();
        String mail = mailtxt.getText().toString().trim();
        String password = passwordtxt.getText().toString().trim();

        if (name.isEmpty()) {
            nametxt.setError("Fill your name");
            nametxt.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            agetxt.setError("Fill your age");
            agetxt.requestFocus();
            return;
        }

        if (mail.isEmpty()) {
            mailtxt.setError("Fill your mailtxt");
            mailtxt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordtxt.setError("Fill your mailtxt");
            passwordtxt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            mailtxt.setError("Valid mailxt");
            mailtxt.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(name,age,mail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(MainActivity.this, "Error 2", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void giavale(View v){
        FirebaseDatabase.getInstance().getReference("Test")
                .child("markos")
                .setValue("Aleksis");
    }

    public void goToLogin(View v){
        Intent intent = new Intent(MainActivity.this,LoginAct.class);
        startActivity(intent);
    }
}