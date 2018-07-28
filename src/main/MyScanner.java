package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyScanner {
    public MyScanner(){

    }

    public static String stringScanner(){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        try {
            inputStr = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStr;
    }
}
