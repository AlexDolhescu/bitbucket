package com.example.andoidpad;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NoteWithInformation extends AppCompatActivity implements OnClickListener {

    MultiAutoCompleteTextView mainview;
    Button btn_bold,btn_italic,btn_normal,btn_size,btn_save;
    String fileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        String text = getIntent().getStringExtra("TEXT");
        String file_path = getIntent().getStringExtra("PATH");
        fileName = file_path;

        btn_bold = (Button) findViewById(R.id.boldtext);
        btn_italic =(Button) findViewById(R.id.italictext);
        btn_normal =(Button) findViewById(R.id.normaltext);
        btn_size =(Button) findViewById(R.id.fontsize);
        btn_save =(Button) findViewById(R.id.save);


        mainview = (MultiAutoCompleteTextView) findViewById(R.id.mainpart);

        mainview.setText(text);

        String appnametitle ="AndroidPad - ";
        setTitle(appnametitle+fileName);

        btn_bold.setOnClickListener(this);
        btn_italic.setOnClickListener(this);
        btn_normal.setOnClickListener(this);
        btn_size.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        registerForContextMenu(btn_size);
    }
    public  void onClick(View view) {
        if (view == btn_bold) {
            //Typeface setfontstyle = Typeface.defaultFromStyle(Typeface.BOLD);
            //mainview.setTypeface(setfontstyle);
            SpannableStringBuilder str = new SpannableStringBuilder(mainview.getText().toString());
            str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), mainview.getSelectionStart(), mainview.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mainview.setText(str);
            Toast.makeText(getApplicationContext(), "Bold", Toast.LENGTH_LONG).show();
        } else if (view == btn_italic) {
            //Typeface setfontstyle = Typeface.defaultFromStyle(Typeface.ITALIC);
            //mainview.setTypeface(setfontstyle);
            SpannableStringBuilder str = new SpannableStringBuilder(mainview.getText().toString());
            str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.ITALIC), mainview.getSelectionStart(), mainview.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mainview.setText(str);
            Toast.makeText(getApplicationContext(), "Italic", Toast.LENGTH_LONG).show();
        } else if (view == btn_normal) {
            //Typeface setfontstyle = Typeface.defaultFromStyle(Typeface.NORMAL);
            //mainview.setTypeface(setfontstyle);
            SpannableStringBuilder str = new SpannableStringBuilder(mainview.getText().toString());
            str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.NORMAL), mainview.getSelectionStart(), mainview.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mainview.setText(str);
            Toast.makeText(getApplicationContext(), "Normal", Toast.LENGTH_LONG).show();
        }
        else if (view == btn_size) {
            view.showContextMenu();
            Toast.makeText(getApplicationContext(), "Font Size", Toast.LENGTH_LONG).show();
        }
        else if(view == btn_save)
        {
            String text = mainview.getText().toString();
            FileOutputStream fos = null;
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
            try {
                fos = new FileOutputStream(file);
                fos.write(text.getBytes());

                Toast.makeText(this,"Saved to" + getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select Font Size");
        menu.add(0, v.getId(), 0, "5+");
        menu.add(0, v.getId(), 0, "10+");
        menu.add(0, v.getId(), 0, "20+");
        menu.add(0, v.getId(), 0, "25+");
        menu.add(0, v.getId(), 0, "30+");
        menu.add(0, v.getId(), 0, "35+");
        menu.add(0, v.getId(), 0, "40+");
        menu.add(0, v.getId(), 0, "45+");
        menu.add(0, v.getId(), 0, "50");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        if (item.getTitle() == "5+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        } else if (item.getTitle() == "10+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        } else if (item.getTitle() == "20+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        } else if (item.getTitle() == "25+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        } else if (item.getTitle() == "30+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        } else if (item.getTitle() == "35+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33);
        } else if (item.getTitle() == "40+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 43);
        } else if (item.getTitle() == "45+") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
        } else if (item.getTitle() == "50") {
            mainview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        } else {
            Toast.makeText(getApplicationContext(), "Something is Worng", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


}