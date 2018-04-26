package com.example.android.to_do_list;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mTaskList;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mTaskList = findViewById(R.id.task_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTaskList.setLayoutManager(mLayoutManager);
        mDatabase.child("Tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Task> tasks = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Task task = snapshot.getValue(Task.class);
                    tasks.add(task);
                }
                CustomAdapter customAdapter = new CustomAdapter(tasks);
                mTaskList.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.keepSynced(true);

        TextView bannerDay = findViewById(R.id.bannerday);
        TextView bannerDate = findViewById(R.id.bannerdate);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        bannerDay.setText(dayOfTheWeek);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdff = new SimpleDateFormat("MMM MM");
        String dateString =sdff.format(date);
        bannerDate.setText(dateString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.addTask){
            Intent intent = new Intent(MainActivity.this,AddTask.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
