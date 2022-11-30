package com.example.tarea_ix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    ImageView image;
    Button insert, view, edit, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        image = findViewById(R.id.new_image);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Userlist.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Integer imageValue = image.getImageAlpha();

                Boolean checkinsertdata  = DB.insertdata(nameTXT, imageValue);
                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this, "Nuevo alimento insertado", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "El nuevo alimento no pudo ser insertado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Integer imageValue = image.getImageAlpha();

                Boolean checkeditdata  = DB.editdata(nameTXT, imageValue);
                if(checkeditdata==true)
                {
                    Toast.makeText(MainActivity.this, "Alimento editado", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "El alimento no pudo ser editado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata  = DB.deletedata(nameTXT);
                if(checkdeletedata==true)
                {
                    Toast.makeText(MainActivity.this, "Alimento eliminado", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "El alimento no pudo ser eliminado", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}