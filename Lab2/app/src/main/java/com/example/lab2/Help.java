package com.example.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView txtpass;
    Button btnshowpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        txtpass = (TextView) findViewById(R.id.pass);
        btnshowpass = (Button) findViewById(R.id.showpass);

        btnshowpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(Help.this);
                builder.setMessage("Chiar vrei sa vezi?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtpass.setText("alexdolhescu");
                            }
                        })
                        .setNegativeButton("Cancel",null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
