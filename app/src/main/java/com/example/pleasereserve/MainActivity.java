package com.example.pleasereserve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    Button btn, timeBtn, doneBtn, callBtn;
    EditText nameEdit,callEdit,seatEdit;
    DatabaseReference databaseReference;

    String num = "01534593853";

    Spinner spinner;

    String timeArray[] = {"10:00 am - 1:00 pm","1:00 pm - 4:00 pm", "4:00 pm - 7:00 pm", "7:00 pm - 10:00 pm"};


    ArrayAdapter <String> adapter;

    String record= "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("reservation");

        btn = findViewById(R.id.saveButtonID);
        nameEdit = findViewById(R.id.referenceNameID);
        callEdit = findViewById(R.id.referenceCellID);
        seatEdit = findViewById(R.id.seatNoID);
        timeBtn = findViewById(R.id.timeButtonID);
        doneBtn = findViewById(R.id.doneButtonID);
        callBtn = findViewById(R.id.callButtonID);

        spinner = (Spinner) findViewById(R.id.timeSpinID);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, timeArray);



        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use postion value

                switch (position)

                {

                    case 0:

                        record = "10:00 am - 1:00 pm";

                        break;

                    case 1:

                        record = "1:00 pm - 4:00 pm";

                        break;

                    case 2:

                        record = "4:00 pm - 7:00 pm";

                        break;

                    case 3:

                        record = "7:00 pm - 10:00 pm";

                        break;

                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetTime();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Retrieve.class);
                startActivity(intent);
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                String number = num;

                intentCall.setData(Uri.parse("Tel: " + number));

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT);
                    requestPermission();
                }

                else{
                    startActivity(intentCall);
                }

            }
        });

    }

    public void addData(){
        String name = nameEdit.getText().toString().trim();
        String call = callEdit.getText().toString().trim();
        String seat = seatEdit.getText().toString().trim();
        String time = spinner.getSelectedItem().toString().trim();

        String key=databaseReference.push().getKey();

        Restaurant restaurant=new Restaurant (name,call,seat,time);
        databaseReference.child(key).setValue(restaurant);
        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
    }

    public void clickexit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void showSetTime(){
        Toast.makeText(MainActivity.this, "Time is set", Toast.LENGTH_LONG).show();
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
