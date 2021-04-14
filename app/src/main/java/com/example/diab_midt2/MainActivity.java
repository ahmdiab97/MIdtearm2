package com.example.diab_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;


    EditText ID, Name, email, number;

    LinearLayout updatingLayout;

    int x = 1;// to make my UPDATE layout visble/unvisible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttnAdd = (Button) findViewById(R.id.btninsert);
        Button activety2 = (Button) findViewById(R.id.active2);
        Button activety3 = (Button) findViewById(R.id.active3);


        myDB = new DatabaseHelper(this);


        ID = (EditText) findViewById(R.id.userID);
        Name = (EditText) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.userEmail);
        number = (EditText) findViewById(R.id.number);


        //updatingLayout=(LinearLayout)findViewById(R.id.layout);// the UPDATE layout

        activety2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
        activety3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });


        bttnAdd.setOnClickListener(new View.OnClickListener() {
            //adds a record to the table
            @Override
            public void onClick(View v) {

                if (myDB.addData(Integer.parseInt(ID.getText().toString()), Name.getText().toString(), email.getText().toString(), number.getText().toString())==false)
                    Toast.makeText(MainActivity.this, "Data was not entered into the table \nPlease check your input!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data was successfully entered into the table", Toast.LENGTH_LONG).show();


            }
        });

    }
}