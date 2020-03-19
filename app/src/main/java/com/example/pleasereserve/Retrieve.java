package com.example.pleasereserve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Retrieve extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<Restaurant> membersList;
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        databaseReference= FirebaseDatabase.getInstance().getReference("members");
        membersList=new ArrayList<>();

        adapter=new Adapter(Retrieve.this,membersList);

        listView=findViewById(R.id.listView);

    }
    protected void  onStart(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Restaurant restaurant=dataSnapshot1.getValue(Restaurant.class);
                    membersList.add(restaurant);
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
        Toast.makeText(Retrieve.this, "Successful reservation", Toast.LENGTH_SHORT).show();
    }
}
