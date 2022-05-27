package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ParkingLot extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private Button pl1;
    private Button pl2;
    private Button pl3;
    private Button pl4;
    private Button pl5;
    private Button pl6;
    private Button pl7;
    private Button pl8;
    private Button pl9;
    private Button pl10;
    private Button pl11;
    private Button pl12;
    private Button pl13;
    private Button pl14;
    private Button duser;

    ImageButton refreshh;


    private Button temp; //in refreshF
    private Button temp1; //in reserve function


    private  Firebase mRootRef;
    private  FirebaseAuth mFirebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_lot);

        ///// refreshes activity ---------------//
        refreshF();
        final Handler h = new Handler();
        final int delay = 5000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){

                refreshF(); // refreshes activity
                h.postDelayed(this, delay);
            }
        }, delay);
        //--------------------------------------//


        mFirebaseAuth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://userdata-da93a.firebaseio.com/ParkingLot");



        refreshh = (ImageButton) findViewById(R.id.imageButton2);
        refreshh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ParkingLot.this, "View refreshed!", Toast.LENGTH_SHORT).show();
                refreshF();
            }
        });

//        duser = (Button) findViewById(R.id.button2);
//        duser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                user.delete()
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(ParkingLot.this, "Account deleted!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                Intent intent = new Intent(ParkingLot.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });



        pl1 = (Button) findViewById(R.id.pl1);
        pl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("1", pl1);
            }
        });


        pl2 = (Button) findViewById(R.id.pl2);
        pl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("2", pl2);
            }
        });


        pl3 = (Button) findViewById(R.id.pl3);
        pl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("3", pl3);
            }
        });


        pl4 = (Button) findViewById(R.id.pl4);
        pl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("4", pl4);
            }
        });

        pl5 = (Button) findViewById(R.id.pl5);
        pl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("5", pl5);
            }
        });

        pl6 = (Button) findViewById(R.id.pl6);
        pl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("6", pl6);
            }
        });

        pl7 = (Button) findViewById(R.id.pl7);
        pl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("7", pl7);
            }
        });

        pl8 = (Button) findViewById(R.id.pl8);
        pl8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("8", pl8);
            }
        });

        pl9 = (Button) findViewById(R.id.pl9);
        pl9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("9", pl9);
            }
        });

        pl10 = (Button) findViewById(R.id.pl10);
        pl10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("10", pl10);
            }
        });

        pl11 = (Button) findViewById(R.id.pl11);
        pl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("11", pl11);
            }
        });

        pl12 = (Button) findViewById(R.id.pl12);
        pl12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("12", pl12);
            }
        });

        pl13 = (Button) findViewById(R.id.pl13);
        pl13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("13", pl13);
            }
        });

        pl14 = (Button) findViewById(R.id.pl14);
        pl14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve("14", pl14);
            }
        });

        TextView rateUs = (TextView) findViewById(R.id.rateUs);
        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if (mFirebaseUser == null) {
                    Toast.makeText(ParkingLot.this, "To rate, you have to login first!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(ParkingLot.this, RateApp.class);
                    startActivity(intent);
                }
            }
        });

        TextView reportIssue = (TextView) findViewById(R.id.reportIssue);
        reportIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if (mFirebaseUser == null) {
                    Toast.makeText(ParkingLot.this, "To report a bug, you have to login first!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(ParkingLot.this, BugReport.class);
                    startActivity(intent);
                }
            }
        });



    }



    private void refreshF() {

        Button buttons[] = new Button[14];
        buttons[0] = (Button)findViewById(R.id.pl1);
        buttons[1] = (Button)findViewById(R.id.pl2);
        buttons[2] = (Button)findViewById(R.id.pl3);
        buttons[3] = (Button)findViewById(R.id.pl4);
        buttons[4] = (Button)findViewById(R.id.pl5);
        buttons[5] = (Button)findViewById(R.id.pl6);
        buttons[6] = (Button)findViewById(R.id.pl7);
        buttons[7] = (Button)findViewById(R.id.pl8);
        buttons[8] = (Button)findViewById(R.id.pl9);
        buttons[9] = (Button)findViewById(R.id.pl10);
        buttons[10] = (Button)findViewById(R.id.pl11);
        buttons[11] = (Button)findViewById(R.id.pl12);
        buttons[12] = (Button)findViewById(R.id.pl13);
        buttons[13] = (Button)findViewById(R.id.pl14);

        String[] place = new String[14];
        place[0] = "1";
        place[1] = "2";
        place[2] = "3";
        place[3] = "4";
        place[4] = "5";
        place[5] = "6";
        place[6] = "7";
        place[7] = "8";
        place[8] = "9";
        place[9] = "10";
        place[10] = "11";
        place[11] = "12";
        place[12] = "13";
        place[13] = "14";

        for(int i= 0 ; i<14; i++){

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ParkingLot").child(place[i]);
            int finalI = i;
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String user = snapshot.child("user").getValue(String.class);

                    String reservationDate = snapshot.child("date").getValue(String.class);
                    Date date = new Date();
                    long now = ((date.getTime()) / (60000));
                    long start = Long.parseLong(reservationDate);
                    long difference = now - start;
                    System.out.println(difference);
                    Integer di = (int) (long) difference;


                    final int a = finalI;
                    temp = buttons[a];

                    if(user.equals("freeSpace")){
                        temp.setBackgroundColor(Color.rgb(166, 172, 204));
                        temp.setTextColor(Color.WHITE);

                    }else if(di <=5){
                        temp.setBackgroundColor(Color.rgb(252, 227, 3));
                        temp.setTextColor(Color.BLACK);

                    } else{
                        temp.setBackgroundColor(Color.RED);
                        temp.setTextColor(Color.BLACK);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ParkingLot.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void reserve(String location, Button button){

        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ParkingLot").child(location);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    temp1 = button;
                    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                    String id = mFirebaseUser.getUid();


                    String m = snapshot.child("user").getValue(String.class);
                    Firebase childRef = mRootRef.child(location);

                    String tm = snapshot.child("date").getValue(String.class);
                    Date date = new Date();
                    long now = ((date.getTime()) / (60000));
                    long start = Long.parseLong(tm);
                    long difference = now - start;
                    System.out.println(difference);
                    long diff = difference;
                    Integer dii = (int) (long) diff;
                    difference= difference+ 1000;
                    Integer di = (int) (long) difference;

                    //int sum = di/10;
                    //double y = Double.longBitsToDouble(difference);
                    if(di<1){
                        di = 1000;
                    }
                    String sum1 = String.valueOf(di/10);


                    if (id.equals(m)) {

                        //

                        if(dii <=5){
                            childRef.child("user").setValue("freeSpace");
                            childRef.child("date").setValue("0");
                            temp1.setBackgroundColor(Color.rgb(166, 172, 204));
                            temp1.setTextColor(Color.WHITE);
                            Toast.makeText(ParkingLot.this, "Reservation successfully canceled!", Toast.LENGTH_SHORT).show();
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(ParkingLot.this);
                            builder.setTitle(R.string.app_name);
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("Do you want to end reservation?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            childRef.child("user").setValue("freeSpace");
                                            childRef.child("date").setValue("0");
                                            temp1.setBackgroundColor(Color.rgb(166, 172, 204));
                                            temp1.setTextColor(Color.WHITE);

                                            Intent intent = new Intent(ParkingLot.this, CheckoutActivity.class);
                                            intent.putExtra("sum", sum1);
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

                    } else if(m.equals("freeSpace")){

                        String time = String.valueOf(now);

                        childRef.child("user").setValue(id);
                        childRef.child("date").setValue(time);
                        temp1.setBackgroundColor(Color.rgb(252, 227, 3));
                        temp1.setTextColor(Color.BLACK);
                    }else{
                        Toast.makeText(ParkingLot.this, "This place is already taken!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ParkingLot.this, "Failure!", Toast.LENGTH_SHORT).show();
                }
            });


        }else{
            Toast.makeText(ParkingLot.this, "Please login first!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ParkingLot.this, MainActivity.class);
            startActivity(intent);
        }

    }



    @SuppressLint("WrongViewCast")
    public void showPopup(View v){
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.popup_menu1);
            popup.show();
        }else{
            Toast.makeText(ParkingLot.this, "You have to login first!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.news:
                    Intent intent = new Intent(ParkingLot.this, NewsList.class);
                    startActivity(intent);
                return true;

            case R.id.instructions:
                Intent intent1 = new Intent(ParkingLot.this, InstructionsList.class);
                startActivity(intent1);
                return true;

            case R.id.logout:
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    mFirebaseAuth.signOut();
                    Toast.makeText(ParkingLot.this, "Logout is successful", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(ParkingLot.this, MainActivity.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(ParkingLot.this, "You are not currently logged in!", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.delete_user:

                AlertDialog.Builder builder = new AlertDialog.Builder(ParkingLot.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to delete your account?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ParkingLot.this, "Account deleted!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                Intent intent3 = new Intent(ParkingLot.this, MainActivity.class);
                                startActivity(intent3);


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            case R.id.reviews:
                Intent intent4 = new Intent(ParkingLot.this, ReviewsList.class);
                startActivity(intent4);
                return true;

            default:
                return false;

        }
    }
}