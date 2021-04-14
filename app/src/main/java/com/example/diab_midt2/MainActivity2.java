package com.example.diab_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;


    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDB = new DatabaseHelper(this);


        search = (EditText) findViewById(R.id.editText);

        Button bttnView  = (Button) findViewById(R.id.view);
        Button move  = (Button) findViewById(R.id.active1);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
        bttnView.setOnClickListener(new View.OnClickListener() {
            // if nothing is entered in the employee NAME it will view all enteries in the table
            // if a string was written in the employee NAME it will show the record
            //an error will appear if no results were found

            Cursor cur;
            StringBuffer buffer=new StringBuffer();

            @Override
            public void onClick(View v) {
                if (search.getText().toString().equals("")) {
                    cur = myDB.getListContents();
                } else if (!(search.getText().toString().equals(""))) {
                    cur = myDB.getSpecificResult(search.getText().toString());
                }

                if (cur.getCount()==0)
                    Toast.makeText(MainActivity2.this, "No results found !", Toast.LENGTH_LONG).show();
                else {

                    while (cur.moveToNext()) {
                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());

                }
            }
        });



    }



    }
