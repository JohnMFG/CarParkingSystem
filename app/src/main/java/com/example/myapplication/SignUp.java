package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class SignUp extends AppCompatActivity {

    private EditText PersonNameR, PersonLNameR, PersonEmailR, password1R, password2R;
    private FirebaseAuth firebaseAuth;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        Button cancel = (Button) findViewById(R.id.cancelGoToMain);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });




        register.setOnClickListener(v -> {

            if(validate()){
                //Upload data to the data base
                String user_email = PersonEmailR.getText().toString().trim();
                String user_password = password1R.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(task -> {

                    if(task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                    }else{
                        Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SignUp.this, "Email already taken", Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });


    }

    private void setupUIViews(){
        PersonNameR = (EditText)findViewById(R.id.PersonName);
        PersonLNameR = (EditText)findViewById(R.id.PersonLName);
        PersonEmailR = (EditText)findViewById(R.id.PersonEmail);
        password1R = (EditText)findViewById(R.id.password1);
        password2R = (EditText)findViewById(R.id.password2);
        register = (Button)findViewById(R.id.confirmB);
    }

     Boolean validate(){
        Boolean result = false;

        String Fname = PersonNameR.getText().toString();
        String Lname = PersonLNameR.getText().toString();
        String pswd1 = password1R.getText().toString();
        String pswd2 = password2R.getText().toString();
        String email = PersonEmailR.getText().toString();

        if(Fname.isEmpty() || Lname.isEmpty() || pswd1.isEmpty() || pswd2.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
        }else if(!pswd1.equals(pswd2)){
            Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }

        return result;
    }

}