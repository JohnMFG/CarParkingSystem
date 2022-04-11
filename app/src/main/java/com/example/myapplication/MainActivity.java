package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stripe.android.PaymentConfiguration;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private EditText PersonEmailL, passwordL;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51Kl89YCUfB9y1XeaJPqLH4R7cJtVKnSdhP8evXKuSr8Bek5lOxlfnQrd9Z46zzYfbpKflN32Y1OLjKi15iQukshy00vdIp7mlZ"
        );

        PersonEmailL = (EditText)findViewById(R.id.emailMain);
        passwordL = (EditText)findViewById(R.id.passwordMain);


        firebaseAuth = FirebaseAuth.getInstance();



        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            Intent intent = new Intent(MainActivity.this, ParkingLot.class);
            startActivity(intent);
        }



        Button parkingLot = (Button) findViewById(R.id.goToParkingLot);
        parkingLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParkingLot.class);
                startActivity(intent);
            }
        });

        Button signingIn = (Button) findViewById(R.id.signButton);
        signingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });



        Button login = (Button)findViewById(R.id.loginB);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = PersonEmailL.getText().toString();
                String pw = passwordL.getText().toString();


                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(MainActivity.this, "You are already logged in!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "You can now navigate to parking lot", Toast.LENGTH_SHORT).show();

                }
                else if(em.isEmpty() || pw.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
                }else{
                    validate(PersonEmailL.getText().toString(), passwordL.getText().toString());
                }

            }
        });

    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.news:
                Intent intent = new Intent(MainActivity.this, NewsList.class);
                startActivity(intent);
                return true;
            case R.id.instructions:
                Intent intent1 = new Intent(MainActivity.this, InstructionsList.class);
                startActivity(intent1);
                return true;
            default:
                return false;

        }
    }


    private void validate(String userName, String userPassword){

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ParkingLot.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}