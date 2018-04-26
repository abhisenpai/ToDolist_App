package com.example.android.to_do_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddTask extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    EditText ed1_addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        database = FirebaseDatabase.getInstance();

    }
    public void addButtonClick(View view){
        ed1_addtask =findViewById(R.id.edittask_1);

        String name =ed1_addtask.getText().toString();

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd,yyy h:mm a");
        String dateString = sdf.format(date);

        myRef = database.getInstance().getReference().child("Tasks");
        DatabaseReference newTask = myRef.push();
        newTask.child("name").setValue(name);
        newTask.child("time").setValue(dateString);

        Toast.makeText(getApplicationContext(),"List Added",Toast.LENGTH_SHORT).show();

    }
}
