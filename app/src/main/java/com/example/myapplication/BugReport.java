package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

public class BugReport extends AppCompatActivity {

    EditText report;
    Button submit;
    String temp;

    private Firebase mRootRef;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug_report);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://userdata-da93a.firebaseio.com/ParkingLot");

        report = findViewById(R.id.report);

        submit = findViewById(R.id.submitBtn1);
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                temp = report.getText().toString();

                if(temp.length() <= 200){
                    report.setText("");
                    test(temp);
                    Toast.makeText(BugReport.this, "Thank you for your report!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BugReport.this, ParkingLot.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BugReport.this, "Used to many symbols!(max:200)", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void test(String report){





        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Reports");
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
                reference.child(id).setValue(report);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BugReport.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}