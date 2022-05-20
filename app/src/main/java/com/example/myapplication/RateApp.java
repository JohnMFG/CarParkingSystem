package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class RateApp extends AppCompatActivity {

    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp, temp1, tempRev;

    private  Firebase mRootRef;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_app);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://userdata-da93a.firebaseio.com/ParkingLot");

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        submit = findViewById(R.id.submitBtn);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();

                if(rateValue<=1 && rateValue>0){
                    rateCount.setText("Bad "+rateValue+"/5");
                }else if(rateValue<=2 && rateValue>1){
                    rateCount.setText("Ok "+rateValue+"/5");
                }else if(rateValue<=3 && rateValue>2){
                    rateCount.setText("Good "+rateValue+"/5");
                }else if(rateValue<=4 && rateValue>3){
                    rateCount.setText("Very Good "+rateValue+"/5");
                }else if(rateValue<=5 && rateValue>4){
                    rateCount.setText("Excellent "+rateValue+"/5");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                temp = rateCount.getText().toString();
                tempRev = review.getText().toString();

                if(tempRev.length() <= 30){
                    showRating.setText("Your Rating: \n" +temp+ "\n" + review.getText());
                    temp1 = rateValue + "/5 " + review.getText().toString();
                    review.setText("");
                    ratingBar.setRating(0);
                    rateCount.setText("");
                    Toast.makeText(RateApp.this, temp1, Toast.LENGTH_LONG).show();
                    test(temp1);
                }else{
                    Toast.makeText(RateApp.this, "Used to many symbols!(max:30)", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void test(String review){





            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Reviews");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                    String id = mFirebaseUser.getUid();

                    //String m = snapshot.child("user").getValue(String.class);
                    //Firebase childRef = mRootRef.child("1");

                    //String tm = snapshot.child("date").getValue(String.class);

                    long count = snapshot.getChildrenCount()+1;
                    String count1 = Long.toString(count);
                    //reference.child(count1).setValue(review);
                    reference.child(id).setValue(review);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RateApp.this, "Failure!", Toast.LENGTH_SHORT).show();
                }
            });




    }
}