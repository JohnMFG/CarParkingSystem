package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Credentials extends AppCompatActivity {

    TextView sum;
    private EditText nameCard, cardN, exDate, securityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);
        setupUIViews();

        sum = (TextView) findViewById(R.id.sumID);
        sum.setText(getIntent().getStringExtra("sum"));



        Button pay = (Button) findViewById(R.id.payB);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    Toast.makeText(Credentials.this, "Payment successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Credentials.this, ParkingLot.class);
                    startActivity(intent);
                }

            }
        });


    }

    private void setupUIViews(){
        nameCard = (EditText)findViewById(R.id.cardN);
        cardN = (EditText)findViewById(R.id.cardNum);
        exDate = (EditText)findViewById(R.id.exDate);
        securityCode = (EditText)findViewById(R.id.securityC);
    }

    private Boolean validate(){
        Boolean result = false;

        String NC = nameCard.getText().toString();
        String CN = cardN.getText().toString();
        String ED = exDate.getText().toString();
        String SC = securityCode.getText().toString();

        if(NC.isEmpty() || CN.isEmpty() || ED.isEmpty() || SC.isEmpty()){
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
        }else if((CN.length() != 16) || (ED.length() != 4) || (SC.length() != 3)){
            Toast.makeText(this, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
        }
        else{
            result =true;
        }

        return result;
    }
}