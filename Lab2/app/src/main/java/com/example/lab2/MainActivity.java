package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String GAME_STATE_KEY = "";
    private static final String TEXT_VIEW_KEY = "";
    ListView listView;
    TextView textView;
    private static final String FILE_NAME="example.txt";

    EditText mEditText;

    // some transient state for the activity instance
    String gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);


        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> infoList = new ArrayList<>();

        arrayList.add("VW");
        infoList.add("VW power");
        arrayList.add("BMW");
        infoList.add("BMW power");
        arrayList.add("Audi");
        infoList.add("Audi power");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(infoList.get(position));

            }
        });

        mEditText = findViewById(R.id.editText);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                Toast.makeText(this, "Afisez ce vreau eu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                openHelp();
                return true;
            case R.id.item4:
                openSettings();
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    public void openHelp(){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void openSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void save(View view){
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this,"Saved to" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
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

    public void load(View view){
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //////////////////////////////////////////////////////////////////

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView text=findViewById(R.id.textView);
        outState.putString(TEXT_VIEW_KEY, text.getText().toString());
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("a","start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("a","restart");
    }

    @Override
    protected void onResume() {
        Log.d("a","Resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("a","pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("a","stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("a","destroy");
        super.onDestroy();
    }


}
