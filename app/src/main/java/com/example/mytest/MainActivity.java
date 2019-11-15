package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    String titleCollection;
    Tokenizer tokenizer = new Tokenizer();
    Analyzer analyzer = new Analyzer();
    List<String> nounData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //divide and conquer

        Crawler crawler = new Crawler();
        try {
            titleCollection = crawler.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        tokenizer.setElements(titleCollection);
        tokenizer.tokenize();

        nounData = tokenizer.getStringList();

        analyzer.DataLoad(nounData);

        for(int i=0;i<analyzer.getSize();i++){
            System.out.println("Word = " + analyzer.getWord(i) + "Frequency = " + analyzer.getFrequency(i) +" Time = " + crawler.getTime());
        }


    }
}

