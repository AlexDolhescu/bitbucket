package com.example.andoidpad;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static android.icu.lang.UProperty.INT_START;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;

    String text = new String();
    String file_path = new String();


    Button new_doc;
    Button open_doc;
    Button open_note;
    TextView tv_output;
    EditText fileName;
    TextView openFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_doc = findViewById(R.id.new_doc);
        open_doc = findViewById(R.id.open_doc);
        open_note = findViewById(R.id.open_note);
        tv_output = findViewById(R.id.textView);
        fileName = findViewById(R.id.fileName);
        openFile = findViewById(R.id.openFile);



        new_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_document();
            }
        });
        open_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_document();
            }
        });
        open_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_note();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }

    }

    public void new_document(){
        String fileName2 = fileName.getText().toString();
        fileName2 = fileName2 + ".txt";
        Intent intent = new Intent(this, Note.class);
        intent.putExtra("PATH", fileName2);
        startActivity(intent);
    }

    public void open_document(){
        performFileSearch();
    }

    public void open_note(){
        Intent intent = new Intent(getBaseContext(), NoteWithInformation.class);
        intent.putExtra("TEXT", text);
        intent.putExtra("PATH", file_path);
        startActivity(intent);
    }

    private String readText(String input){
        File file = new File(Environment.getExternalStorageDirectory(), input);
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    private void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == READ_REQUEST_CODE){
            if(data != null){
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                if(path.contains("emulated")){
                    path = path.substring(path.indexOf("0") + 1);
                }
                Toast.makeText(this, "" + path, Toast.LENGTH_LONG).show();
                //tv_output.setText(readText(path));
                //SpannableStringBuilder str = new SpannableStringBuilder("Your awesome text");
                //str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //tv_output.setText(str);
                text = readText(path);
                file_path = path;
                openFile.setText(file_path);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
