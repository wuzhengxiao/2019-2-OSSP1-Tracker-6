package com.example.myapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WordCloud {

    Vector<Word> words= new Vector<>();

    //current wc 만들때 사용되는 생성자
    public WordCloud(String ID, String PW){
        String contents=crawler(ID,PW);
        makeWC(contents);
        int size= length();
    }

    // 과거 wc 만들때 사용되는 생성자
    public WordCloud() {

    }

    //temp function
    /*
        실제 크롤링 해서 형태소 단위로 나누고 이걸 [조국, 23, 2019] 이런 형태의 문자열로
        출력해주는 함수로 대체해야함
    */
    public String crawler(String id, String pw){
        File file= new File("text.txt");
        // 여기서 오류 발생
        char[] ch= new char[(int)file.length()];

        StringBuilder str= new StringBuilder();

        try{
            BufferedReader br= new BufferedReader(new FileReader(file));
            br.read(ch);
            str.append(ch);
            br.close();
        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){
            System.out.println(e);
        }
        String contents= str.toString();
        return contents;
    }
    //출력은 name freq date 꼴로 문자열이 쭉 이어짐


    public void makeWC(String contents){
        String[] array= contents.split(" ");
        int length= array.length;
        String name="";
        int freq=0;
        String date="";

       for(int i=0;i<length;++i){
           if(i%3==0){
               name=array[i];
           }
           else if(i%3==1){
               freq=Integer.parseInt(array[i]);
           }
           else{
               date=array[i];
               //워드 객체화 후 vector push_back
               words.add(new Word(name,freq,date));
            }
       }
    }

    public int length(){
        return words.size();
    }


}
