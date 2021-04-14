package com.example.diab_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new DatabaseHelper(this);


        name = (EditText) findViewById(R.id.nameuser);
        setContentView(R.layout.activity_main3);
        Button bttnViewDataOnList = (Button) findViewById(R.id.button1);
        Button delete = (Button) findViewById(R.id.button2);
        bttnViewDataOnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, list.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            //deletes a row specified  by the employee_NAME
            //then display a toast with the count of rows deleted
            //if no rows are found, display a toast saying that nothing was deleted
            @Override
            public void onClick(View v) {

               myDB.dltRow(name.getText().toString());

            }
        });
    }
}