package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class MainActivity extends AppCompatActivity  {

    Button[] buttons;
    WordCloud wc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // do not erase
        wc=new WordCloud("tracker","1234");
        int size=wc.length();

        Button[] buttons= new Button[size];
        for(int i=0;i<size;++i){
            buttons[i].setText(wc.words.get(i).getName());
        }

        ViewGroup viewGroup=findViewById(R.id.main);
        for(int i=0;i<size;++i){
            viewGroup.addView(buttons[i]);
        }

    }

}
